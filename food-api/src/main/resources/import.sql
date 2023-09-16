insert into tbl_cozinha (id, nome) values (1, 'Francesa');
insert into tbl_cozinha (id, nome) values (2, 'Mexicana');
insert into tbl_cozinha (id, nome) values (3, 'Brasileira');

insert into tbl_restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Président', 10, 1);
insert into tbl_restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Cão Velho', 9.50, 1);
insert into tbl_restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Coco Bambu', 15, 2);

insert into tbl_estado (id, nome) values (1, 'Minas Gerais');
insert into tbl_estado (id, nome) values (2, 'São Paulo');
insert into tbl_estado (id, nome) values (3, 'Pará');

insert into tbl_cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into tbl_cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into tbl_cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into tbl_cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into tbl_cidade (id, nome, estado_id) values (5, 'Canaã dos Carajás', 3);

insert into tbl_forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into tbl_forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into tbl_forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into tbl_permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into tbl_permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');