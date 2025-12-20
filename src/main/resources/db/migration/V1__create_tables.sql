CREATE TABLE nivel_risco (
  id BIGSERIAL PRIMARY KEY,
  nivel_risco VARCHAR(255) NOT NULL
);

CREATE TABLE predictions ( -- mudar para previsao
  id BIGSERIAL PRIMARY KEY,
  previsao VARCHAR(100) NOT NULL, -- mudar para tipo_previsao
  probabilidade DOUBLE PRECISION NOT NULL,
  nivel_risco VARCHAR(255),
  recomendacao VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE genero (
  id BIGSERIAL PRIMARY KEY,
  genero VARCHAR(255) NOT NULL
);