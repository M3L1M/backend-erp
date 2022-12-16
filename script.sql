CREATE DATABASE seniorteste;

CREATE SCHEMA senior;

CREATE TABLE senior.pedido
(
  id uuid NOT NULL,
  data_pedido date,
  desconto integer,
  situacao character varying(8),
  preco numeric(16,2),
  CONSTRAINT pedido_pkey PRIMARY KEY (id)
);

CREATE TABLE senior.produto_servico
(
  id uuid NOT NULL,
  descricao character varying(150),
  tipo character varying(8),
  preco numeric(16,2),
  quantidade integer,
  status character varying(8),
  CONSTRAINT produto_servico_pkey PRIMARY KEY (id)
);

CREATE TABLE senior.produtoservico_pedido
(
  id uuid NOT NULL,
  quantidade integer,
  id_pedido uuid NOT NULL,
  id_produto_servico uuid NOT NULL,
  CONSTRAINT produtoservico_pedido_pkey PRIMARY KEY (id),
  CONSTRAINT produtoservico_pedido_id_pedido_fkey FOREIGN KEY (id_pedido)
      REFERENCES senior.pedido (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT produtoservico_pedido_id_produto_servico_fkey FOREIGN KEY (id_produto_servico)
      REFERENCES senior.produto_servico (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
