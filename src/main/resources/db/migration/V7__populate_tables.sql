-- Populando tabela employer
INSERT INTO employer (id_employer, fantasyname, razaosocial, cnpj, email) VALUES
  ('00000000-0000-0000-0000-000000000001', 'Tech Solutions', 'Tech Solutions LTDA', '12345678000199', 'contato@techsolutions.com'),
  ('00000000-0000-0000-0000-000000000002', 'AgroMais', 'AgroMais S.A.', '98765432000188', 'contato@agromais.com'),
  ('00000000-0000-0000-0000-000000000003', 'Construtora Alpha', 'Construtora Alpha Ltda', '11223344000177', 'contato@alpha.com'),
  ('00000000-0000-0000-0000-000000000004', 'EducaPro', 'EducaPro Educacional', '22334455000166', 'contato@educapro.com'),
  ('00000000-0000-0000-0000-000000000005', 'SaudeMais', 'SaudeMais Hospitalar', '33445566000155', 'contato@saudemais.com');

-- Populando tabela employee
INSERT INTO employee (id_employee, id_employer, name, cpf, position, hours, salary, status, inactivation_date, gender, civil_state, email) VALUES
('10000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001', 'João Silva', '12345678901', 'Desenvolvedor', '40', 5000.00, 'ATIVO', NULL, 'MASCULINO', 'SOLTEIRO', 'joao.silva@techsolutions.com'),
('10000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000002', 'Maria Souza', '98765432100', 'Engenheira Agrônoma', '44', 6200.00, 'ATIVO', NULL, 'FEMININO', 'CASADO', 'maria.souza@agromais.com'),
('10000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000003', 'Carlos Lima', '11223344556', 'Mestre de Obras', '40', 4800.00, 'ATIVO', NULL, 'MASCULINO', 'CASADO', 'carlos.lima@alpha.com'),
('10000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-000000000004', 'Ana Paula', '22334455667', 'Professora', '30', 3500.00, 'ATIVO', NULL, 'FEMININO', 'SOLTEIRO', 'ana.paula@educapro.com'),
('10000000-0000-0000-0000-000000000005', '00000000-0000-0000-0000-000000000005', 'Roberto Costa', '33445566778', 'Enfermeiro', '36', 4200.00, 'ATIVO', NULL, 'MASCULINO', 'DIVORCIADO', 'roberto.costa@saudemais.com');

-- Populando tabela user
INSERT INTO "user" (id_user, name, email, password, id_employer, role) VALUES
  ('20000000-0000-0000-0000-000000000001', 'Admin Tech', 'admin.tech@techsolutions.com', 'senha123', '00000000-0000-0000-0000-000000000001', 'ADMIN'),
  ('20000000-0000-0000-0000-000000000002', 'Admin Agro', 'admin.agro@agromais.com', 'senha123', '00000000-0000-0000-0000-000000000002', 'ADMIN'),
  ('20000000-0000-0000-0000-000000000003', 'Admin Alpha', 'admin.alpha@alpha.com', 'senha123', '00000000-0000-0000-0000-000000000003', 'ADMIN'),
  ('20000000-0000-0000-0000-000000000004', 'Admin Educa', 'admin.educa@educapro.com', 'senha123', '00000000-0000-0000-0000-000000000004', 'ADMIN'),
  ('20000000-0000-0000-0000-000000000005', 'Admin Saude', 'admin.saude@saudemais.com', 'senha123', '00000000-0000-0000-0000-000000000005', 'ADMIN');

-- Populando tabela payroll
INSERT INTO payroll (id_payroll, id_employee, id_employer, reference_month, base_salary, gross_total, net_total, total_deductions, inss, irrf) VALUES
  ('30000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001', '2025-10', 5000.00, 5200.00, 4100.00, 1100.00, 400.00, 200.00),
  ('30000000-0000-0000-0000-000000000002', '10000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000002', '2025-10', 6200.00, 6400.00, 5000.00, 1400.00, 500.00, 300.00),
  ('30000000-0000-0000-0000-000000000003', '10000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000003', '2025-10', 4800.00, 5000.00, 3900.00, 1100.00, 350.00, 180.00),
  ('30000000-0000-0000-0000-000000000004', '10000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-000000000004', '2025-10', 3500.00, 3700.00, 2900.00, 800.00, 250.00, 120.00),
  ('30000000-0000-0000-0000-000000000005', '10000000-0000-0000-0000-000000000005', '00000000-0000-0000-0000-000000000005', '2025-10', 4200.00, 4400.00, 3400.00, 1000.00, 300.00, 150.00);

