-- Kustutab public schema (mis põhimõtteliselt kustutab kõik tabelid)
DROP SCHEMA IF EXISTS janek24 CASCADE;
-- Loob uue public schema vajalikud õigused
CREATE SCHEMA janek24
-- taastab vajalikud andmebaasi õigused
    GRANT ALL ON SCHEMA janek24 TO postgres;
GRANT ALL ON SCHEMA janek24 TO PUBLIC;