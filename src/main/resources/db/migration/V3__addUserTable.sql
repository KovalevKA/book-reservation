CREATE TABLE IF NOT EXISTS "users" (
                                        "user_id" BIGSERIAL PRIMARY KEY,
                                        "user_login" varchar,
                                        "user_pass" varchar,
                                        "user_role" varchar
);
CREATE TABLE IF NOT EXISTS "roles" (
                                      "role_id" BIGSERIAL PRIMARY KEY,
                                      "name" varchar
);

CREATE TABLE IF NOT EXISTS "users_roles" (
                                    "user_id" BIGINT,
                                    "role_id" BIGINT
);