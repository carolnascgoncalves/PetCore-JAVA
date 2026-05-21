# 🐾 PetCore – Backend Java Spring Boot
## Challenger - 2026

## 🗣️ Dev do projeto
#### NOME: Emanuelly Ventura Do Nascimento

RM562339 - 2TDSPJ

#### NOME: Carolina Nascimento Gonçalves

RM564786 - 2TDSPJ

#### NOME: Julia Sayuri Kina

RM564555 - 2TDSPJ

# Descrição do Projeto

O PetCore é um sistema veterinário desenvolvido com Java e Spring Boot com o objetivo de auxiliar no gerenciamento de informações médicas de pets. O sistema permite o cadastro de tutores, médicos veterinários, pets, prontuários, receitas, exames, relatórios e clínicas.

O backend foi desenvolvido utilizando arquitetura REST API, permitindo comunicação com aplicações frontend web ou mobile.

A proposta do projeto é centralizar informações veterinárias em um único sistema, facilitando o acompanhamento do histórico médico dos animais, controle de consultas, receitas e exames.

---

# Solução do Projeto

A solução foi construída utilizando Spring Boot e Spring Data JPA para gerenciamento da API e persistência de dados.

O sistema foi organizado em camadas:

* Resource (Controllers);
* Service;
* Repository;
* Entity;
* DTO.

Essa separação melhora a manutenção, reutilização e organização do código.

O backend também utiliza:

* CRUD completo;
* Relacionamentos entre entidades;
* UUID como identificador;
* DTOs para transferência de dados;
* Paginação;
* Validações;
* Endpoints REST.

---

# Funcionalidades

## 👤 Tutor

* Cadastro de tutor;
* Login com email e senha;
* Atualização de dados;
* Busca de tutor por ID;
* Listagem de tutores;
* Exclusão de tutor.

---

## 🩺 Médico Veterinário

* Cadastro de médicos;
* Login;
* Atualização de perfil;
* Exclusão;
* Associação com prontuários, exames e receitas.

---

## 🐶 Pet

* Cadastro de pets;
* Atualização de status;
* Atualização de imagem;
* Busca de pets;
* Associação com histórico médico.

---

## 📋 Histórico

* Criação de histórico médico;
* Associação com prontuários;
* Associação com relatórios;
* Associação com pets.

---

## 📄 Prontuário

* Cadastro de prontuários;
* Associação com médico;
* Associação com exames e receitas;
* Atualização de descrição.

---

## 💊 Receita e Medicamentos

* Cadastro de receitas;
* Associação de medicamentos;
* Controle de dosagem e instruções.

---

## 🧪 Exames

* Cadastro de exames;
* Associação com prontuários;
* Controle de tipo e data.

---

## 🏥 Clínica e Endereço

* Cadastro de clínicas;
* Associação de endereço;
* Controle de relatórios.

---

## 📑 Relatórios

* Cadastro de relatórios médicos;
* Associação com histórico;
* Associação com clínicas e médicos.

---

# Tecnologias Utilizadas

## ☕ Backend

* Java 21;
* Spring Boot;
* Spring Data JPA;
* Hibernate;
* Maven.

---

## 🗄️ Banco de Dados

* Oracle Database.

---

## 🔧 Ferramentas

* IntelliJ IDEA;
* Postman;
* Swagger/OpenAPI;
* Git;
* GitHub;
* Docker.

---

## 📚 Bibliotecas

* Lombok;
* Jakarta Validation;
* Spring Web.

---

# Cronograma de Desenvolvimento

| Etapa | Responsável           | Atividade                                         | Prazo    |
| ----- | --------------------- | ------------------------------------------------- | -------- |
| 1     | Equipe Backend        | Levantamento de requisitos e modelagem do sistema | Semana 1 |
| 2     | Equipe Backend        | Definição das entidades e relacionamentos         | Semana 1 |
| 3     | Desenvolvedor Backend | Criação da estrutura Spring Boot                  | Semana 2 |
| 4     | Desenvolvedor Backend | Implementação das entidades JPA                   | Semana 2 |
| 5     | Desenvolvedor Backend | Criação dos repositories                          | Semana 2 |
| 6     | Desenvolvedor Backend | Implementação dos services                        | Semana 3 |
| 7     | Desenvolvedor Backend | Desenvolvimento dos endpoints REST                | Semana 3 |
| 8     | Desenvolvedor Backend | Implementação das regras de negócio               | Semana 4 |
| 9     | Desenvolvedor Backend | Implementação de login de Tutor e Médico          | Semana 4 |
| 10    | Equipe Backend        | Testes dos endpoints com Postman                  | Semana 5 |
| 11    | Equipe Backend        | Correção de bugs e ajustes finais                 | Semana 5 |
| 12    | Equipe Backend        | Organização da documentação e README              | Semana 6 |

---

# Estrutura do Projeto

```bash
src/main/java
│
├── datasource
│   └── repositories
│
├── domainmodel
│   ├── entities
│   └── enums
│
├── resources
│   └── dtos
│
├── services
│
├── config
│
└── BackendApplication.java
```

---

# Endpoints Principais

## TUTORES
### ------------ GET ------------
FetchAll
http://localhost:8080/api/tutor/listar?page=0&size=10

FetchById
http://localhost:8080/api/tutor/90c87b51-422c-4a4e-83ca-eb73fdc1932a


### ------------ POST ------------
FetchByEmail(login)
http://localhost:8080/api/tutor
{
  "email": "sandraReg@gmail.com",
  "senha": "523453466256"
}

Create
http://localhost:8080/api/tutor/login
{
  "nome": "Yuki Fernandes",
  "dataNascimento": "2009-05-21",
  "telefone": "11950282323",
  "sexo": "M",
  "email": "yukiHotoru@gmail.com",
  "senha": "1029384756"
}


### ------------ PATCH ------------
update 
http://localhost:8080/api/tutor/90c87b51-422c-4a4e-83ca-eb73fdc1932a
{
  "email": "sandraRegina@gmail.com"
  "telefone": "11945414016",
  "senha": "Senha12345",
  "urlImg": "pathImage/sandra.png"
}

### ------------ DELETE ------------
http://localhost:8080/api/tutor/90c87b51-422c-4a4e-83ca-eb73fdc1932a



## RELATÓRIO
### ------------ GET ------------
fetchAll
http://localhost:8080/api/relatorio/listar?page=0&size=10

fetchById
http://localhost:8080/api/relatorio/3fa6e8fb-9b5e-48d8-b079-9eb40139766a


### ------------ POST ------------
create
http://localhost:8080/api/relatorio
{
  "observacao": "Paciente apresentou episódios de febre.",
  "idHist": "0b5920dc-c674-4df9-a1b2-d94acb659443",
  "idMedico": "47d44b69-bfa6-4c9e-a01a-bb5698596797"
}

### ------------ PATCH ------------
http://localhost:8080/api/relatorio/3fa6e8fb-9b5e-48d8-b079-9eb40139766a
{
  "observacao": "Paciente apresentou episódios de febre e fadiga durante a consulta."
}

### ------------ DELETE ------------
http://localhost:8080/api/relatorio/3fa6e8fb-9b5e-48d8-b079-9eb40139766a

## RECEITA
### ------------ GET ------------
fetchAll
http://localhost:8080/api/receita/listar?page=0&size=10

fetchById
http://localhost:8080/api/receita/0efc7b5b-1f83-476e-9fc1-7be4360ab9c0


### ------------ POST ------------
create
http://localhost:8080/api/receita
{
  "nome": "Receita reduzida hormonal",
  "validade": "2026-07-14",
  "idMedico": "47d44b69-bfa6-4c9e-a01a-bb5698596797",
  "idMedicamentos": [
    "9be6cf8f-9e06-4dea-b767-6de2c552536d"
  ]
}
### ------------ PATCH ------------
http://localhost:8080/api/relatorio/3fa6e8fb-9b5e-48d8-b079-9eb40139766a
{
  "observacao": "Paciente apresentou episódios de febre e fadiga durante a consulta."
}

### ------------ DELETE ------------
http://localhost:8080/api/receita/0efc7b5b-1f83-476e-9fc1-7be4360ab9c0

## PRONTUÁRIO
### ------------ GET ------------
fetchAll
http://localhost:8080/api/prontuario/listar?page=0&size=10

fetchById
http://localhost:8080/api/prontuario/20407246-c6c1-476c-b567-0f65cb685172


### ------------ POST ------------
create
http://localhost:8080/api/prontuario
{
  "data": "2026-05-15",
  "descricao": "Paciente apresentou melhoras sobre a alteração hormonal. Não é mais necessário controle contínuo.",
  "idMedico": "47d44b69-bfa6-4c9e-a01a-bb5698596797",
  "idHistorico": "64c6b86a-17d6-4693-912c-331d83ae1c06",
  "idExames": [
    "abd73853-85dd-43b8-934d-febe4cf1f661"
  ],
  "idReceitas": []
}


### ------------ PATCH ------------
http://localhost:8080/api/prontuario/20407246-c6c1-476c-b567-0f65cb685172
{
  "descricao": "Paciente apresentou alteração hormonal. Necessário controle contínuo."
}

### ------------ DELETE ------------
http://localhost:8080/api/prontuario/20407246-c6c1-476c-b567-0f65cb685172

## PET 
### ------------ GET ------------
fetchAll
http://localhost:8080/api/pet/listar?page=0&size=10

fetchMenuAll
http://localhost:8080/api/pet/menu/listar?page=0&size=10

fetchById
http://localhost:8080/api/pet/1405e532-7085-4a07-80e8-5d36e4d35c44


### ------------ POST ------------
create
http://localhost:8080/api/prontuario

{
  "nome": "Robson",
  "dataNasc": "2023-01-17",
  "sexo": "M",
  "urlImg": "pathImage/robson.png",
  "status": "ATIVO",
  "porte": "grande",
  "especie": "cachorro",
  "raca": "bordercollie",
  "pelagem": "branco e preto"
}

### ------------ PATCH ------------
updateImage
http://localhost:8080/api/pet/imagem/1405e532-7085-4a07-80e8-5d36e4d35c44
{
  "urlImg": "pathImage/olimpio.img"
}


updateStatus
http://localhost:8080/api/pet/status/1405e532-7085-4a07-80e8-5d36e4d35c44
{
  "statusEnum": "ATIVO"
}
### ------------ DELETE ------------
http://localhost:8080/api/pet/1405e532-7085-4a07-80e8-5d36e4d35c44

## MÉDICO
### ------------ GET ------------
FetchAll
http://localhost:8080/api/medico/listar?page=0&size=10

FetchById
http://localhost:8080/api/medico/47d44b69-bfa6-4c9e-a01a-bb5698596797


### ------------ POST ------------
FetchByEmail(login)
http://localhost:8080/api/medico/login
{
  "email": "renatoGon@gmail.com",
  "senha": "08945706"
}


Create
http://localhost:8080/api/medico
{
  "nome": "Eliane Barbosa",
  "dataNascimento": "1987-09-19",
  "telefone": "11992846192",
  "email": "enailEBarb@gmail.com",
  "sexo": "F",
  "senha": "0192837465",
  "especialidade": "Endocrinologia",
  "urlImg": "pathImage/eliane.png"
}


### ------------ PATCH ------------
update 
http://localhost:8080/api/medico/47d44b69-bfa6-4c9e-a01a-bb5698596797
{
  "email": "renatogoncalves@gmail.com",
  "urlImg": "pathImage/renato.png"
}

### ------------ DELETE ------------
http://localhost:8080/api/medico/47d44b69-bfa6-4c9e-a01a-bb5698596797

## MEDICAMENTO 
### ------------ GET ------------
FetchAll
http://localhost:8080/api/medicamentos/listar?page=0&size=10

FetchById
http://localhost:8080/api/medicamentos/9be6cf8f-9e06-4dea-b767-6de2c552536d

### ------------ POST ------------
Create
http://localhost:8080/api/medicamentos
{
  "nome": "Dipirona",
  "dosagem": "1g",
  "instrucao": "Tomar em caso de dores"
}


### ------------ PATCH ------------
update 
http://localhost:8080/api/medicamentos/9be6cf8f-9e06-4dea-b767-6de2c552536d
{
  "dosagem": "1g",
  "instrucao": "Tomar de 12 em 12 horas"
}

### ------------ DELETE ------------
http://localhost:8080/api/medicamentos/9be6cf8f-9e06-4dea-b767-6de2c552536d

## HISTÓRICO 
### ------------ GET ------------
FetchAll
http://localhost:8080/api/historico/listar?page=0&size=10

FetchById
http://localhost:8080/api/historico/0b5920dc-c674-4df9-a1b2-d94acb659443

### ------------ POST ------------
create
* COLOCAR NOVO ID DE PET -> criar
{
  "data": "2024-06-20",
  "status": "ATIVO",
  "idProntuarios": [
    "20407246-c6c1-476c-b567-0f65cb685172"
  ],

  "idPet": ""
}


### ------------ DELETE ------------
http://localhost:8080/api/historico/0b5920dc-c674-4df9-a1b2-d94acb659443

## EXAME 
### ------------ GET ------------
FetchAll
http://localhost:8080/api/exame/listar?page=0&size=10

FetchById
http://localhost:8080/api/exame/ad257493-f6d3-4dc4-be4a-cc3c25fba540

### ------------ POST ------------
Create
http://localhost:8080/api/exame
{
  "nome": "Hemograma",
  "data": "2026-12-10",
  "tipo": "Exame de sangue",
  "idMedico": "47d44b69-bfa6-4c9e-a01a-bb5698596797",
  "idProntuario": "20407246-c6c1-476c-b567-0f65cb685172"
}
### ------------ PATCH ------------
update 
http://localhost:8080/api/exame/ad257493-f6d3-4dc4-be4a-cc3c25fba540
{
  "nome": "Teste de urina",
  "data": "2025-01-07",
  "tipo": "Exame parasitológico"
}

### ------------ DELETE ------------
http://localhost:8080/api/exame/ad257493-f6d3-4dc4-be4a-cc3c25fba540

## ENDEREÇO
### ------------ GET ------------
FetchAll
http://localhost:8080/api/endereco/listar?page=0&size=10

FetchById
http://localhost:8080/api/endereco/174bcf8c-b2ce-496c-bc35-710813a65836

### ------------ POST ------------
Create
http://localhost:8080/api/endereco
{
  "cep": "04347010",
  "complemento": "Casa A"
}
### ------------ PATCH ------------
update 
http://localhost:8080/api/endereco/174bcf8c-b2ce-496c-bc35-710813a65836
{
  "cep": "01100111"
}
### ------------ DELETE ------------
http://localhost:8080/api/endereco/174bcf8c-b2ce-496c-bc35-710813a65836
OBS: Não é possível deletar um endereço associado à uma clínica

## CLÍNICA
### ------------ GET ------------
FetchAll
http://localhost:8080/api/clinica/listar?page=0&size=10

FetchById
http://localhost:8080/api/clinica/7d4cc917-cc9f-45b9-95ab-fe195014e898

### ------------ POST ------------
Create
http://localhost:8080/api/clinica
{
  "cnpj": "12345678912345",
  "nome": "clinica passinhos",

  "idEndereco": ""
}
OBS: Não é possivel criar uma clinica com um endereço ja associado a outra clinica
### ------------ PATCH ------------
update 
http://localhost:8080/api/clinica/7d4cc917-cc9f-45b9-95ab-fe195014e898 
{
  "nome": "Clinica petzinhos",
  "idEndereco": "174bcf8c-b2ce-496c-bc35-710813a65836"
}
### ------------ DELETE ------------
http://localhost:8080/api/endereco/174bcf8c-b2ce-496c-bc35-710813a65836
OBS: Não é possível deletar um endereço associado à uma clínica


---

# Objetivo do Projeto

O principal objetivo do PetCore é facilitar o gerenciamento veterinário através de um sistema organizado, seguro e centralizado, permitindo que clínicas e tutores acompanhem todas as informações médicas dos pets de forma eficiente.

---

# Repositório

Adicionar link do GitHub:

```bash
https://github.com/carolnascgoncalves/PetCore-JAVA.git
```

