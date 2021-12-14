CREATE TABLE sessao (
    cd_sessao INT NOT NULL AUTO_INCREMENT,
    cd_pauta INT NOT NULL,
    data_Inicio VARCHAR(255) NOT NULL,
    data_fim VARCHAR(255) NOT NULL,
    PRIMARY KEY (cd_sessao),
    FOREIGN KEY (cd_pauta) REFERENCES pauta(cd_pauta)
);