CREATE TABLE explicabilidade_previsao (
  previsao_id BIGINT NOT NULL,
  fator VARCHAR(100) NOT NULL,
  PRIMARY KEY (previsao_id, fator),
  FOREIGN KEY (previsao_id) REFERENCES previsao(id)
);