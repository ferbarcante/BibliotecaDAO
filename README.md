# Biblioteca Virtual - CRUD com Java, PostgreSQL e DAO
## Descrição

Este projeto é uma aplicação de CRUD (Create, Read, Update, Delete) para uma biblioteca virtual, desenvolvida utilizando Java, PostgreSQL e o padrão de projeto DAO (Data Access Object). O objetivo deste projeto é demonstrar a implementação de operações básicas de banco de dados de forma organizada e eficiente, seguindo boas práticas de programação.

### Tecnologias Utilizadas
- Java: Linguagem de programação utilizada para desenvolver a aplicação.
- PostgreSQL: Banco de dados relacional utilizado para armazenar as informações dos livros.
-  DAO (Data Access Object): Padrão de projeto utilizado para abstrair e encapsular o acesso ao banco de dados.
## Utilização
A aplicação pode ser utilizada para gerenciar os livros da biblioteca virtual. As operações CRUD podem ser realizadas através de uma interface de linha de comando

## Exemplo de uso
 Adicionar um Livro:
 ```java
    Livro livro = new Livro();
    livro.setTitulo("Os Irmãos Karamázov");
    livro.setPreco(111);
    livro.setCategoria("Ficção filosófica");
    livro.setIsbn("978-8497930772");

    LivroDAO livroDAO = new LivroDAO()
```

Consultar Livros:
```java
for(Livro l : livroDAO.listar()){
  System.out.println("Livro: " + l.getTitulo());
}

livroDAO.listar();
```

Atualizar um Livro:
```java
Livro livroAtualizado = new Livro();
livroAtualizado.setId_livro(1); // ID do livro que será atualizado
livroAtualizado.setTitulo("Novo Título do Livro");
livroAtualizado.setPreco(49.99);
livroAtualizado.setIsbn("123-4567890123");
livroAtualizado.setCategoria("Fantasia");
livroAtualizado.setId_editora(2);

LivroDAO livroDAO = new LivroDAO();
livroDAO.update(livroAtualizado);
```
Remover um Livro:
```java
livro.setId_livro(1); 
LivroDAO livroDAO = new LivroDAO();
livroDAO.delete(livroParaDeletar);
```
