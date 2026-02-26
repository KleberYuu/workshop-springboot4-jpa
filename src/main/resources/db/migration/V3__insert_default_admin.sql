INSERT INTO tb_user (email, password)
SELECT 'admin@admin.com', '$2a$10$yUAP5E8mmySaG3.Bx6i84ecmIqdFVdAiiiifKTVlfSFbKnRelGBXq'
    WHERE NOT EXISTS (
    SELECT 1 FROM tb_user WHERE email = 'admin@admin.com'
);

INSERT INTO tb_user_role (user_id, role_id)
SELECT u.id, r.id
FROM tb_user u, tb_role r
WHERE u.email = 'admin@admin.com'
  AND r.authority = 'ROLE_ADMIN'
  AND NOT EXISTS (
    SELECT 1
    FROM tb_user_role ur
    WHERE ur.user_id = u.id
      AND ur.role_id = r.id
);