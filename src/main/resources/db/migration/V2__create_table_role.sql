create table tb_role (
    id BIGSERIAL PRIMARY KEY,
    authority VARCHAR(100) NOT NULL UNIQUE
);

create table tb_user_role (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES tb_user(id),
    FOREIGN KEY (role_id) REFERENCES tb_role(id)
);

INSERT INTO tb_role (authority) VALUES ('ROLE_USER');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');
