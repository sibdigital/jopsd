INSERT INTO public.kpi (name, query, is_deleted) VALUES ('о количестве результатов и показателей, находящихся на сопровождении пользователя', 'select count(*) as value
         from targets as t
                  inner join
              (
                  select *
                  from members as m
                  where m.user_id in (:user_id)
              ) as mb
              using (project_id)', false);
INSERT INTO public.kpi (name, query, is_deleted) VALUES ('о количестве внесенных в реестр рисков', 'select count(*) as value
from risks as r
         inner join
     (
         select *
         from members as m
         where m.user_id in (:user_id)
     ) as mb
     using (project_id)', false);
INSERT INTO public.kpi (name, query, is_deleted) VALUES ('о количестве результатов и показателей, находящихся на сопровождении пользователя, имеющих отклонения', 'select count(*) as value
from targets as t
         inner join
     (
         select *
         from members as m
         where m.user_id in (:user_id)
     ) as mb
     using (project_id)
where status_id in (select id
                    from enumerations
                    where type = ''TargetStatus''
                      and name in (''Выполнено с отклонениями'', ''Исполняется с отклонениями'',
                                   ''Исполняется с критическими отклонениями''))', false);
INSERT INTO public.kpi (name, query, is_deleted) VALUES ('об отсутствии или наличии просроченных контрольных точек', 'select count(*) as value
from work_packages as w
         inner join
     (
         select *
         from members as m
         where m.user_id in (:user_id)
     ) as mb
     using (project_id)
where due_date < current_date', false);
INSERT INTO public.kpi (name, query, is_deleted) VALUES ('о количестве организованных совещаний, мероприятий, дискуссий, направленных на минимизацию рисков', 'select count(*) as value
from meetings as m
         inner join
     (
         select *
         from members as m
         where m.user_id in (:user_id)
     ) as mb
     using (project_id)', false);
INSERT INTO public.kpi (name, query, is_deleted) VALUES ('о количестве сопровождаемых проектах', 'select count(*) as value
         from members as m
         where m.user_id in (:user_id)', false);
