DELETE FROM Especialidade
DELETE FROM Atividade
DELETE FROM Instrutor
DELETE FROM Instrutor_certificacoes
DELETE FROM Horario
DELETE FROM Horario_sessoes
DELETE FROM instrutorResponsavel
DELETE FROM atividadeAssociada
INSERT INTO Especialidade (id, nome, duracao, certificacaoNecessaria) VALUES (1, 'Fisioterapia',20, 'FIS0')
INSERT INTO Especialidade (id, nome, duracao, certificacaoNecessaria) VALUES (2, 'Massagem Desportiva',30, 'MASG')
INSERT INTO Especialidade (id, nome, duracao, certificacaoNecessaria) VALUES (3, 'Pilates Clinico',40, 'FIS1')
INSERT INTO Especialidade (id, nome, duracao, certificacaoNecessaria) VALUES (4, 'Pos Parto',30, 'FIS0')
INSERT INTO Instrutor(id, numero, nome, version) VALUES (1,1,'Uma',0)
INSERT INTO Instrutor(id, numero, nome, version) VALUES (2,2,'Dona',0)
INSERT INTO Instrutor(id, numero, nome, version) VALUES (3,3,'Tina',0)
INSERT INTO Instrutor_certificacoes(Instrutor_id, certificacoes) VALUES (1,'FIS0')
INSERT INTO Instrutor_certificacoes(Instrutor_id, certificacoes) VALUES (1,'FIS1')
INSERT INTO Instrutor_certificacoes(Instrutor_id, certificacoes) VALUES (1,'MASG')
INSERT INTO Instrutor_certificacoes(Instrutor_id, certificacoes) VALUES (2,'FIS0')
INSERT INTO Instrutor_certificacoes(Instrutor_id, certificacoes) VALUES (2,'MASG')
INSERT INTO Instrutor_certificacoes(Instrutor_id, certificacoes) VALUES (3,'MASG')