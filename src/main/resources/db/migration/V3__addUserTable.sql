CREATE TABLE IF NOT EXISTS "users" (
                                        "user_id" BIGSERIAL PRIMARY KEY,
                                        "username" varchar,
                                        "password" varchar,
                                        "first_name" varchar,
                                        "last_name" varchar,
                                        "status" varchar,
                                        "created" date,
                                        "updated" date
);
CREATE TABLE IF NOT EXISTS "roles" (
                                      "role_id" BIGSERIAL PRIMARY KEY,
                                      "name" varchar
);

CREATE TABLE IF NOT EXISTS "users_roles" (
                                    "user_id" BIGINT,
                                    "role_id" BIGINT
);