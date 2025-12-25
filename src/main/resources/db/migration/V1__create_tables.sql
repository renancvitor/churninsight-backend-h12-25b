CREATE TABLE nivel_risco (
  id INTEGER PRIMARY KEY,
  nivel_risco VARCHAR(100) NOT NULL
);

CREATE TABLE tipo_previsao (
  id INTEGER PRIMARY KEY,
  tipo_previsao VARCHAR(100) NOT NULL
);

CREATE TABLE genero (
  id INTEGER PRIMARY KEY,
  genero VARCHAR(100) NOT NULL
);

CREATE TABLE pais (
  id INTEGER PRIMARY KEY,
  pais VARCHAR(100) NOT NULL
);

CREATE TABLE previsao (
  id BIGSERIAL PRIMARY KEY,
  tipo_previsao_id INTEGER NOT NULL,
  nivel_risco_id INTEGER NOT NULL,
  probabilidade DOUBLE PRECISION NOT NULL,
  recomendacao TEXT NOT NULL,
  FOREIGN KEY (tipo_previsao_id) REFERENCES tipo_previsao(id),
  FOREIGN KEY (nivel_risco_id) REFERENCES nivel_risco(id)
);
