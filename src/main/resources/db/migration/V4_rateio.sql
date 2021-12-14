CREATE TABLE rateio (
    cd_rateio INT NOT NULL AUTO_INCREMENT,
    cd_sessao INT NOT NULL,
    cd_pauta INT NOT NULL,
    qtd_sim INT NOT NULL,
    qtd_nao INT NOT NULL,
    data_fim VARCHAR(255) NOT NULL,
    PRIMARY KEY (cd_rateio),
    FOREIGN KEY (cd_pauta) REFERENCES pauta(cd_pauta),
    FOREIGN KEY (cd_sessao) REFERENCES sessao(cd_sessao)
);