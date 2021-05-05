package ru.sibdigital.jopsd.service.mp;

import lombok.extern.slf4j.Slf4j;
import net.sf.mpxj.MPXJException;
import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.ResourceAssignment;
import net.sf.mpxj.Task;
import net.sf.mpxj.reader.UniversalProjectReader;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.mp.MPWorkPackage;
import ru.sibdigital.jopsd.model.Relation;
import ru.sibdigital.jopsd.model.WorkPackage;
import ru.sibdigital.jopsd.model.enums.Statuses;
import ru.sibdigital.jopsd.model.enums.Types;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MPServiceImpl extends SuperServiceImpl implements MPService {

    @Override
    public void importFiles() throws MPXJException {
        File[] directoryList = fileService.getSubfolders(mmpPath);
        for (File dir : directoryList) {
            Long projectId = getProjectIdByDir(dir);

            if (projectId != null) {
                Collection<File> files = fileService.getFiles(dir);
                if (files != null && !files.isEmpty()) {
                    for (File file: files) {
                        processMPFiles(file, projectId);
                    }
                }
            }
        }
    }

    private void processMPFiles(File file, Long projectId) {
        try {
            UniversalProjectReader reader = new UniversalProjectReader();
            ProjectFile projectFile = reader.read(file);
            List<MPWorkPackage> mpWorkPackages = getMpWorkPackages(projectFile, projectId);
            getAndSaveWorkPackages(mpWorkPackages);
            getAndSaveRelations(mpWorkPackages);
        } catch (MPXJException e) {
            logError(e);
        }
    }

    @Override
    public WorkPackage createNewWorkPackage(Task task, Long projectId) {
        Long overallPercentComplete = (task.getOverallPercentComplete() != null) ? task.getOverallPercentComplete().longValue() : 0;

        WorkPackage wp = WorkPackage.builder()
                        .projectId(projectId)
                        .outerId(Long.valueOf(task.getID()))
                        .typeId(Types.CHECK_POINT.getValue())
                        .subject(task.getName())
                        .description(task.getNotes())
                        .startDate(task.getStart())
                        .dueDate(task.getFinish())
                        .statusId(Statuses.IN_WORK.getValue())
                        .authorId(Long.valueOf(2)) // admin
                        .lockVersion(Long.valueOf(0)) // 0
                        .doneRatio(overallPercentComplete)
                        .build();

        return wp;
    }

    private List<MPWorkPackage> getMpWorkPackages(ProjectFile projectFile, Long projectId) {
        List<MPWorkPackage> mpWorkPackages = new ArrayList<>();
        List<ResourceAssignment> ralist = projectFile.getResourceAssignments();
        for (ResourceAssignment resourceAssignment: ralist ) {
            Task task = resourceAssignment.getTask();

            WorkPackage wp = createNewWorkPackage(task, projectId);
            MPWorkPackage mpWp = MPWorkPackage.builder()
                    .mpId(task.getID())
                    .mpParentId((task.getParentTask() != null) ? task.getParentTask().getID() : null)
                    .workPackage(wp)
                    .build();

            mpWorkPackages.add(mpWp);
        }
        return mpWorkPackages;
    }

    private void getAndSaveWorkPackages(List<MPWorkPackage> mpWorkPackages) {
        List<WorkPackage> workPackages = mpWorkPackages.stream()
                .map(ctr -> ctr.getWorkPackage())
                .collect(Collectors.toList());

        workPackageRepo.saveAll(workPackages);
    }

    private void getAndSaveRelations(List<MPWorkPackage> mpWorkPackages) {
        Map<Integer, MPWorkPackage> map = new HashMap<>();
        mpWorkPackages.forEach(ctr -> map.put(ctr.getMpId(), ctr));

        List<Relation> relations = new ArrayList<>();

        for (MPWorkPackage mpWorkPackage : mpWorkPackages) {
            WorkPackage wp = mpWorkPackage.getWorkPackage();
            Long wpId = wp.getId();

            MPWorkPackage mpParentWorkPackage = mpWorkPackage;
            Integer hierarchy = 0;
            while (mpParentWorkPackage != null) {
                WorkPackage parentWp = mpParentWorkPackage.getWorkPackage();
                Relation relation = Relation.builder()
                                    .fromId(parentWp.getId())
                                    .toId(wpId)
                                    .hierarchy(hierarchy)
                                    .count(1)
                                    .build();
                relations.add(relation);

                if (mpParentWorkPackage.getMpParentId() != null) {
                    mpParentWorkPackage = map.get(mpParentWorkPackage.getMpParentId());
                    hierarchy = hierarchy + 1;
                } else {
                    mpParentWorkPackage = null;
                }
            }
        }

        relationRepo.saveAll(relations);
    }

    private Long getProjectIdByDir(File dir) {
        String projectIdString = dir.getName();
        Long projectId = null;
        try {
            projectId = Long.valueOf(projectIdString);
        } catch (NumberFormatException e) {
            logError(e);
        }

        return  projectId;
    }
}
