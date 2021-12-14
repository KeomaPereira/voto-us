CREATE TABLE voto (
    cd_voto INT NOT NULL AUTO_INCREMENT,
    cpf INT NOT NULL,
    cd_sessao INT NOT NULL,
    voto VARCHAR(255) NOT NULL,
    PRIMARY KEY (cd_voto),
    FOREIGN KEY (cd_sessao) REFERENCES sessao(cd_sessao)
);