--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: dietary_restrictions; Type: TABLE; Schema: public; Owner: oop
--

CREATE TABLE public.dietary_restrictions (
    id integer NOT NULL,
    restriction_name character varying(100)
);


ALTER TABLE public.dietary_restrictions OWNER TO oop;

--
-- Name: disastervictim; Type: TABLE; Schema: public; Owner: oop
--

CREATE TABLE public.disastervictim (
    id integer NOT NULL,
    firstname character varying(100) NOT NULL,
    lastname character varying(100),
    age integer,
    birthdate date,
    comments character varying(500),
    locationid integer,
    gender character varying(100),
    entrydate date NOT NULL
);


ALTER TABLE public.disastervictim OWNER TO oop;

--
-- Name: disastervictim_id_seq; Type: SEQUENCE; Schema: public; Owner: oop
--

CREATE SEQUENCE public.disastervictim_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.disastervictim_id_seq OWNER TO oop;

--
-- Name: disastervictim_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: oop
--

ALTER SEQUENCE public.disastervictim_id_seq OWNED BY public.disastervictim.id;


--
-- Name: familyrelations; Type: TABLE; Schema: public; Owner: oop
--

CREATE TABLE public.familyrelations (
    firstperson integer NOT NULL,
    secondperson integer NOT NULL,
    relationship character varying(100)
);


ALTER TABLE public.familyrelations OWNER TO oop;

--
-- Name: inquirer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inquirer (
    id integer NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50),
    phonenumber character varying(20) NOT NULL
);


ALTER TABLE public.inquirer OWNER TO postgres;

--
-- Name: inquirer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.inquirer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.inquirer_id_seq OWNER TO postgres;

--
-- Name: inquirer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.inquirer_id_seq OWNED BY public.inquirer.id;


--
-- Name: inquiry_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inquiry_log (
    id integer NOT NULL,
    inquirer integer NOT NULL,
    calldate date NOT NULL,
    details character varying(500) NOT NULL
);


ALTER TABLE public.inquiry_log OWNER TO postgres;

--
-- Name: inquiry_log_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.inquiry_log_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.inquiry_log_id_seq OWNER TO postgres;

--
-- Name: inquiry_log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.inquiry_log_id_seq OWNED BY public.inquiry_log.id;


--
-- Name: location; Type: TABLE; Schema: public; Owner: oop
--

CREATE TABLE public.location (
    id integer NOT NULL,
    name character varying(100)
);


ALTER TABLE public.location OWNER TO oop;

--
-- Name: location_id_seq; Type: SEQUENCE; Schema: public; Owner: oop
--

CREATE SEQUENCE public.location_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.location_id_seq OWNER TO oop;

--
-- Name: location_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: oop
--

ALTER SEQUENCE public.location_id_seq OWNED BY public.location.id;


--
-- Name: medicalrecord; Type: TABLE; Schema: public; Owner: oop
--

CREATE TABLE public.medicalrecord (
    victimid integer,
    details character varying(1000),
    treatmentdate date,
    locationid integer
);


ALTER TABLE public.medicalrecord OWNER TO oop;

--
-- Name: victim_dietary_restrictions; Type: TABLE; Schema: public; Owner: oop
--

CREATE TABLE public.victim_dietary_restrictions (
    victimid integer,
    restrictionid integer
);


ALTER TABLE public.victim_dietary_restrictions OWNER TO oop;

--
-- Name: disastervictim id; Type: DEFAULT; Schema: public; Owner: oop
--

ALTER TABLE ONLY public.disastervictim ALTER COLUMN id SET DEFAULT nextval('public.disastervictim_id_seq'::regclass);


--
-- Name: inquirer id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inquirer ALTER COLUMN id SET DEFAULT nextval('public.inquirer_id_seq'::regclass);


--
-- Name: inquiry_log id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inquiry_log ALTER COLUMN id SET DEFAULT nextval('public.inquiry_log_id_seq'::regclass);


--
-- Name: location id; Type: DEFAULT; Schema: public; Owner: oop
--

ALTER TABLE ONLY public.location ALTER COLUMN id SET DEFAULT nextval('public.location_id_seq'::regclass);


--
-- Data for Name: dietary_restrictions; Type: TABLE DATA; Schema: public; Owner: oop
--

COPY public.dietary_restrictions (id, restriction_name) FROM stdin;
1	Asian vegetarian
2	Diabetic
3	Gluten intolerant
4	Kosher
5	Low salt
6	Muslim
7	Peanut-free
8	Vegan
9	Vegetarian Jain
\.


--
-- Data for Name: disastervictim; Type: TABLE DATA; Schema: public; Owner: oop
--

COPY public.disastervictim (id, firstname, lastname, age, birthdate, comments, locationid, gender, entrydate) FROM stdin;
1	Leslie	Knope	\N	\N	\N	2	woman	2024-03-11
2	Praveen	\N	\N	1995-12-12	\N	2	non-binary	2024-03-11
3	Margs	\N	71	\N	\N	2	\N	2024-03-11
\.


--
-- Data for Name: familyrelations; Type: TABLE DATA; Schema: public; Owner: oop
--

COPY public.familyrelations (firstperson, secondperson, relationship) FROM stdin;
1	2	siblings
2	3	parent-child
1	3	parent-child
\.


--
-- Data for Name: inquirer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inquirer (id, firstname, lastname, phonenumber) FROM stdin;
1	Dominik	Pflug	123-456-9831
2	Yaa	Odei	123-456-8913
3	Cecilia	Cobos	123-456-7891
4	Hongjoo	Park	123-456-8912
5	Saartje	\N	123-456-7234
6	Urjoshi	\N	456-123-4281
7	Jeb	Jeb	403-480-3800
8	Diane	Lewis	403-111-1111
9	Ben	Wyatt	403-222-2222
10	Myrtle	Beach	555-555-5555
11	April	Ludgate	444-444-4444
12	Tammy	Swanson	000-000-0000
\.


--
-- Data for Name: inquiry_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inquiry_log (id, inquirer, calldate, details) FROM stdin;
1	1	2024-02-28	Theresa Pflug
2	2	2024-02-28	Offer to assist as volunteer
3	3	2024-03-01	Valesk Souza
4	1	2024-03-01	Theresa Pflug
5	1	2024-03-02	Theresa Pflug
6	4	2024-03-02	Yoyo Jefferson and Roisin Fitzgerald
7	5	2024-03-02	Henk Wouters
8	3	2024-03-03	Melinda
9	6	2024-03-04	Julius
10	7	2023-05-05	Looking for mom
11	1	2024-03-05	Leslie Knope
12	8	2024-04-04	Ron Swanson
\.


--
-- Data for Name: location; Type: TABLE DATA; Schema: public; Owner: oop
--

COPY public.location (id, name) FROM stdin;
1	University of Calgary
2	Safe Shelter
\.


--
-- Data for Name: medicalrecord; Type: TABLE DATA; Schema: public; Owner: oop
--

COPY public.medicalrecord (victimid, details, treatmentdate, locationid) FROM stdin;
1	Performed check up.	2024-03-15	2
\.


--
-- Data for Name: victim_dietary_restrictions; Type: TABLE DATA; Schema: public; Owner: oop
--

COPY public.victim_dietary_restrictions (victimid, restrictionid) FROM stdin;
1	3
1	2
2	5
\.


--
-- Name: disastervictim_id_seq; Type: SEQUENCE SET; Schema: public; Owner: oop
--

SELECT pg_catalog.setval('public.disastervictim_id_seq', 1, false);


--
-- Name: inquirer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.inquirer_id_seq', 1, false);


--
-- Name: inquiry_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.inquiry_log_id_seq', 5, true);


--
-- Name: location_id_seq; Type: SEQUENCE SET; Schema: public; Owner: oop
--

SELECT pg_catalog.setval('public.location_id_seq', 1, false);


--
-- Name: dietary_restrictions dietary_restrictions_pkey; Type: CONSTRAINT; Schema: public; Owner: oop
--

ALTER TABLE ONLY public.dietary_restrictions
    ADD CONSTRAINT dietary_restrictions_pkey PRIMARY KEY (id);


--
-- Name: disastervictim disastervictim_pkey; Type: CONSTRAINT; Schema: public; Owner: oop
--

ALTER TABLE ONLY public.disastervictim
    ADD CONSTRAINT disastervictim_pkey PRIMARY KEY (id);


--
-- Name: familyrelations familyrelationskey; Type: CONSTRAINT; Schema: public; Owner: oop
--

ALTER TABLE ONLY public.familyrelations
    ADD CONSTRAINT familyrelationskey PRIMARY KEY (firstperson, secondperson);


--
-- Name: inquirer inquirer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inquirer
    ADD CONSTRAINT inquirer_pkey PRIMARY KEY (id);


--
-- Name: inquiry_log inquiry_log_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inquiry_log
    ADD CONSTRAINT inquiry_log_pkey PRIMARY KEY (id);


--
-- Name: location location_pkey; Type: CONSTRAINT; Schema: public; Owner: oop
--

ALTER TABLE ONLY public.location
    ADD CONSTRAINT location_pkey PRIMARY KEY (id);


--
-- Name: disastervictim disastervictim_locationid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: oop
--

ALTER TABLE ONLY public.disastervictim
    ADD CONSTRAINT disastervictim_locationid_fkey FOREIGN KEY (locationid) REFERENCES public.location(id);


--
-- Name: familyrelations familyrelations_firstperson_fkey; Type: FK CONSTRAINT; Schema: public; Owner: oop
--

ALTER TABLE ONLY public.familyrelations
    ADD CONSTRAINT familyrelations_firstperson_fkey FOREIGN KEY (firstperson) REFERENCES public.disastervictim(id);


--
-- Name: familyrelations familyrelations_secondperson_fkey; Type: FK CONSTRAINT; Schema: public; Owner: oop
--

ALTER TABLE ONLY public.familyrelations
    ADD CONSTRAINT familyrelations_secondperson_fkey FOREIGN KEY (secondperson) REFERENCES public.disastervictim(id);


--
-- Name: inquiry_log inquiry_log_inquirer_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inquiry_log
    ADD CONSTRAINT inquiry_log_inquirer_fkey FOREIGN KEY (inquirer) REFERENCES public.inquirer(id) ON UPDATE CASCADE;


--
-- Name: medicalrecord medicalrecord_locationid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: oop
--

ALTER TABLE ONLY public.medicalrecord
    ADD CONSTRAINT medicalrecord_locationid_fkey FOREIGN KEY (locationid) REFERENCES public.location(id);


--
-- Name: medicalrecord medicalrecord_victimid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: oop
--

ALTER TABLE ONLY public.medicalrecord
    ADD CONSTRAINT medicalrecord_victimid_fkey FOREIGN KEY (victimid) REFERENCES public.disastervictim(id);


--
-- Name: victim_dietary_restrictions victim_dietary_restrictions_restrictionid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: oop
--

ALTER TABLE ONLY public.victim_dietary_restrictions
    ADD CONSTRAINT victim_dietary_restrictions_restrictionid_fkey FOREIGN KEY (restrictionid) REFERENCES public.dietary_restrictions(id);


--
-- Name: victim_dietary_restrictions victim_dietary_restrictions_victimid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: oop
--

ALTER TABLE ONLY public.victim_dietary_restrictions
    ADD CONSTRAINT victim_dietary_restrictions_victimid_fkey FOREIGN KEY (victimid) REFERENCES public.disastervictim(id);


--
-- Name: TABLE inquirer; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.inquirer TO oop;


--
-- Name: SEQUENCE inquirer_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.inquirer_id_seq TO oop;


--
-- Name: TABLE inquiry_log; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.inquiry_log TO oop;


--
-- Name: SEQUENCE inquiry_log_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.inquiry_log_id_seq TO oop;


--
-- PostgreSQL database dump complete
--

