package ru.sibdigital.jopsd.service.mp;

import lombok.extern.slf4j.Slf4j;
import net.sf.mpxj.MPXJException;
import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.Task;
import net.sf.mpxj.reader.UniversalProjectReader;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.mp.MPWorkPackage;
import ru.sibdigital.jopsd.model.Relation;
import ru.sibdigital.jopsd.model.WorkPackage;
import ru.sibdigital.jopsd.model.enums.Statuses;
import ru.sibdigital.jopsd.model.enums.Types;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MPServiceImpl extends SuperServiceImpl implements MPService {

    @Override
    public List<WorkPackage> importFile(InputStream inputStream, Map<String, Object> params) throws MPXJException {
        List<WorkPackage> wps = processMPFile(inputStream, params);
        return wps;
    }

    private List<WorkPackage> processMPFile(InputStream inputStream, Map<String, Object> params) throws MPXJException {
        ProjectFile mpFile = readMPFile(inputStream);
        List<Task> tasks = mpFile.getTasks();
        List<MPWorkPackage> mpWorkPackages = generateMpWorkPackages(tasks, params);
        List<WorkPackage> wps = saveData(mpWorkPackages);
        return wps;
    }

    private List<WorkPackage> saveData(List<MPWorkPackage> mpWorkPackages) {
        List<WorkPackage> wps = getAndSaveWorkPackages(mpWorkPackages);
        getAndSaveRelations(mpWorkPackages);

        return wps;
    }

    private ProjectFile readMPFile(InputStream inputStream) throws MPXJException {
        UniversalProjectReader reader = new UniversalProjectReader();
        ProjectFile projectFile = reader.read(inputStream);
        return projectFile;
    }

    private List<MPWorkPackage> generateMpWorkPackages(List<Task> tasks, Map<String, Object> params) {
        List<MPWorkPackage> mpWorkPackages = new ArrayList<>();
        Map<Long, WorkPackage> foundWPMap = findWorkPackagesInDBByTaskIds(tasks);
        for (Task task : tasks) {
            if (task.getID() != 0) {
                Map<String, Object> newParams = paramsWithWPId(params, task, foundWPMap);
                WorkPackage workPackage = changeOrCreateWorkPackage(task, newParams);
                MPWorkPackage mpWp = MPWorkPackage.builder()
                        .mpId(task.getID())
                        .mpParentId((task.getParentTask() != null) ? task.getParentTask().getID() : null)
                        .workPackage(workPackage)
                        .build();

                mpWorkPackages.add(mpWp);
            }
        }

        return mpWorkPackages;
    }

    private List<WorkPackage> getAndSaveWorkPackages(List<MPWorkPackage> mpWorkPackages) {
        List<WorkPackage> workPackages = mpWorkPackages.stream()
                .map(ctr -> ctr.getWorkPackage())
                .collect(Collectors.toList());

        workPackageRepo.saveAll(workPackages);

        return workPackages;
    }

    private void getAndSaveRelations(List<MPWorkPackage> mpWorkPackages) {
        Map<Integer, MPWorkPackage> map = mpWorkPackages.stream()
                                .collect(Collectors.toMap(MPWorkPackage::getMpId, workPackage -> workPackage));
        List<Long> wpIds = mpWorkPackages.stream()
                            .map(ctr -> ctr.getWorkPackage().getId())
                            .collect(Collectors.toList());

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
                                    .relates(0)
                                    .duplicates(0)
                                    .blocks(0)
                                    .follows(0)
                                    .commonstart(0)
                                    .commonfinish(0)
                                    .includes(0)
                                    .requires(0)
                                    .count(1)
                                    .build();
                relations.add(relation);

                if (mpParentWorkPackage.getMpParentId() != null && mpParentWorkPackage.getMpParentId() != 0) {
                    mpParentWorkPackage = map.get(mpParentWorkPackage.getMpParentId());
                    hierarchy = hierarchy + 1;
                } else {
                    mpParentWorkPackage = null;
                }
            }
        }

        relationRepo.deleteWorkPackagesRelations(wpIds);
        relationRepo.saveAll(relations);
    }

    private WorkPackage changeOrCreateWorkPackage(Task task, Map<String, Object> params) {
        Long overallPercentComplete = (task.getOverallPercentComplete() != null) ? task.getOverallPercentComplete().longValue() : 0;
        Long id = (Long) params.get("id");
        Long projectId = (Long) params.get("projectId");
        Long authorId = (Long) params.get("authorId");

        WorkPackage wp = WorkPackage.builder()
                .id(id)
                .projectId(projectId)
                .outerId(Long.valueOf(task.getID()))
                .typeId(Types.CHECK_POINT.getValue())
                .subject(task.getName())
                .description(task.getNotes())
                .startDate(task.getStart())
                .dueDate(task.getFinish())
                .statusId(Statuses.NOT_STARTED.getValue())
                .authorId(authorId)
                .lockVersion(Long.valueOf(0)) // 0
                .doneRatio(overallPercentComplete)
                .build();

        return wp;
    }

    private Long findWorkPackageId(Map<Long, WorkPackage> foundWPMap, Long outerId) {
        Long wpId = null;

        WorkPackage wp = foundWPMap.get(outerId);
        if (wp != null) {
            wpId = wp.getId();
        }

        return wpId;
    }

    private Map<Long, WorkPackage> findWorkPackagesInDBByTaskIds(List<Task> tasks) {
        List<Long> outerIds = tasks.stream()
                .map(ctr -> Long.valueOf(ctr.getID()))
                .collect(Collectors.toList());

        List<WorkPackage> foundWP = workPackageRepo.findAllByOuterIds(outerIds);
        Map<Long, WorkPackage> foundWPMap = foundWP.stream()
                                            .collect(Collectors.toMap(WorkPackage::getOuterId, ctr -> ctr));

        return foundWPMap;
    }

    private Map<String, Object> paramsWithWPId(Map<String, Object> params, Task task, Map<Long, WorkPackage> foundWPMap) {
        Long outerId = Long.valueOf(task.getID());
        Long wpId = findWorkPackageId(foundWPMap, outerId);
        Map<String, Object> newParams = new HashMap<>(params);
        newParams.put("id", wpId);

        return newParams;
    }

}
