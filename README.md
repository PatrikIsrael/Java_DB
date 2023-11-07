### Atividade :
Este projeto consiste em um sistema de gerenciamento de filmes, desenvolvido em Java Swing, com integração ao banco de dados MySQL. O objetivo é permitir que os usuários cadastrem, atualizem e excluam informações sobre filmes, além de oferecer funcionalidades adicionais.

## Funcionalidades Principais

- Cadastro de Podcasts: Permite aos usuários adicionar novos podcasts ao sistema, fornecendo informações como título, descrição, gênero, episódios, etc.

- Atualização de Dados: Possibilita a edição e atualização de informações sobre podcasts já cadastrados.

- Exclusão de Podcasts: Permite a remoção de podcasts do banco de dados.

- Autenticação de Usuário: Implementa um sistema de autenticação, onde os usuários precisam fazer login para acessar o sistema. Existem três tipos de usuários:

Admin: Tem permissões totais (cadastro, atualização e exclusão de podcasts).
Dev: Tem permissões totais (cadastro, atualização e exclusão de podcasts).
Usuário: Pode apenas consultar os podcasts.
Restrições de Usuário no Banco de Dados: Garante que apenas usuários autorizados possam realizar operações no banco de dados.

## Tecnologias Utilizadas

- Java Swing
- MySQL
- Java Maven
- JPA
- Hibernate
