CREATE TABLE user_account
(
  login text NOT NULL,
  email text,
  "name" text,
  "password" text,
  points integer,
  CONSTRAINT user_account_pkey PRIMARY KEY (login)
);

CREATE SEQUENCE topic_id_topic_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE topic
(
  id_topic integer NOT NULL DEFAULT nextval('topic_id_topic_seq'::regclass),
  title text,
  "content" text,
  login text,
  CONSTRAINT topic_pkey PRIMARY KEY (id_topic),
  CONSTRAINT topic_login_fkey FOREIGN KEY (login)
      REFERENCES user_account (login) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE SEQUENCE comment_id_comment_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE topic_comment
(
  id_comment integer NOT NULL DEFAULT nextval('comment_id_comment_seq'::regclass),
  "comment" text,
  login text,
  id_topic integer,
  CONSTRAINT comment_pkey PRIMARY KEY (id_comment),
  CONSTRAINT comment_id_topic_fkey FOREIGN KEY (id_topic)
      REFERENCES topic (id_topic) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT comment_login_fkey FOREIGN KEY (login)
      REFERENCES user_account (login) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
