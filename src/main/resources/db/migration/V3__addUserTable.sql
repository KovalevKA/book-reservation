CREATE TABLE IF NOT EXISTS "user" (
                                        "user_id" BIGSERIAL PRIMARY KEY,
                                        "user_login" varchar,
                                        "user_pass" varchar,
                                        "user_role" varchar
);
CREATE TABLE IF NOT EXISTS "role" (
                                      "role_id" BIGSERIAL PRIMARY KEY,
                                      "name" varchar
);

CREATE TABLE IF NOT EXISTS "user_role" (
                                    "user_id" BIGINT,
                                    "role_id" BIGINT
);