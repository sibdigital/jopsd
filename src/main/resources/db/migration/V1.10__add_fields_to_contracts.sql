-- НМЦК (Начальная максимальная цена контракта)
ALTER TABLE contracts
    ADD COLUMN IF NOT EXISTS nmck numeric(15, 2);

-- Плановая дата размещения плана-графика закупок
ALTER TABLE contracts
    ADD COLUMN IF NOT EXISTS schedule_date_plan date;

-- Плановая дата размещения извещения
ALTER TABLE contracts
    ADD COLUMN IF NOT EXISTS notification_date_plan date;

-- Фактическая дата размещения извещения
ALTER TABLE contracts
    ADD COLUMN IF NOT EXISTS notification_date date;

-- Плановая дата проведения аукциона
ALTER TABLE contracts
    ADD COLUMN IF NOT EXISTS auction_date_plan date;

-- Плановая дата заключения контракта
ALTER TABLE contracts
    ADD COLUMN IF NOT EXISTS contract_date_plan date;

-- Плановая дата исполнения контракта
ALTER TABLE contracts
    ADD COLUMN IF NOT EXISTS date_end_plan date;

-- Примечание
ALTER TABLE contracts
    ADD COLUMN IF NOT EXISTS note text;

-- Положительное заключение достоверности определения сметной стоимости объекта
-- Реквизиты
ALTER TABLE contracts
    ADD COLUMN IF NOT EXISTS conclusion_of_estimated_cost_details text;
-- Номер
ALTER TABLE contracts
    ADD COLUMN IF NOT EXISTS conclusion_of_estimated_cost_number text;
-- Дата
ALTER TABLE contracts
    ADD COLUMN IF NOT EXISTS conclusion_of_estimated_cost_date date;

-- Положительное заключение государственной экспертизы проектной документации
-- Реквизиты
ALTER TABLE contracts
    ADD COLUMN IF NOT EXISTS conclusion_of_project_documentation_details text;
-- Номер
ALTER TABLE contracts
    ADD COLUMN IF NOT EXISTS conclusion_of_project_documentation_number text;
-- Дата
ALTER TABLE contracts
    ADD COLUMN IF NOT EXISTS conclusion_of_project_documentation_date date;

-- Положительное заключение государственной экологической экспертизы
-- Реквизиты
ALTER TABLE contracts
    ADD COLUMN IF NOT EXISTS conclusion_of_ecological_expertise_details text;
-- Номер
ALTER TABLE contracts
    ADD COLUMN IF NOT EXISTS conclusion_of_ecological_expertise_number text;
-- Дата
ALTER TABLE contracts
    ADD COLUMN IF NOT EXISTS conclusion_of_ecological_expertise_date date;