CREATE TABLE "author" (
                          "author_id" bigint PRIMARY KEY,
                          "name" varchar
);

CREATE TABLE "author_book" (
                               "author_id" bigint,
                               "book_id" bigint
);

CREATE TABLE "translator" (
                              "translator_id" bigint PRIMARY KEY,
                              "name" varchar
);

CREATE TABLE "translator_book" (
                                   "translator_id" bigint,
                                   "book_id" bigint
);

CREATE TABLE "genre" (
                         "genre_id" bigint PRIMARY KEY,
                         "name" varchar
);

CREATE TABLE "genre_book" (
                              "genre_id" bigint,
                              "book_id" bigint
);

CREATE TABLE "book" (
                        "book_id" bigint PRIMARY KEY,
                        "name" varchar,
                        "publishing_house" varchar,
                        "publishing_year" int,
                        "descriptopn" text
);

CREATE TABLE "client" (
                          "client_id" bigint PRIMARY KEY,
                          "name" varchar
);

CREATE TABLE "reserv" (
                          "reserv_id" bigint PRIMARY KEY,
                          "client_id" bigint,
                          "book_id" bigint,
                          "reservation_date" date,
                          "reservation_date_cancel" date
);

ALTER TABLE "author_book" ADD FOREIGN KEY ("author_id") REFERENCES "author" ("author_id");

ALTER TABLE "author_book" ADD FOREIGN KEY ("book_id") REFERENCES "book" ("book_id");

ALTER TABLE "translator_book" ADD FOREIGN KEY ("translator_id") REFERENCES "translator" ("translator_id");

ALTER TABLE "translator_book" ADD FOREIGN KEY ("book_id") REFERENCES "book" ("book_id");

ALTER TABLE "genre_book" ADD FOREIGN KEY ("genre_id") REFERENCES "genre" ("genre_id");

ALTER TABLE "genre_book" ADD FOREIGN KEY ("book_id") REFERENCES "book" ("book_id");

ALTER TABLE "reserv" ADD FOREIGN KEY ("book_id") REFERENCES "book" ("book_id");

ALTER TABLE "reserv" ADD FOREIGN KEY ("client_id") REFERENCES "client" ("client_id");
