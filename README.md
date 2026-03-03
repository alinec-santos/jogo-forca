# Jogo da Forca - JavaFX

Este projeto foi desenvolvido como Trabalho Prático da disciplina de Programação Orientada a Objetos na Universidade Federal de Viçosa (UFV) - Campus Florestal. O objetivo principal foi criar um Jogo da Forca interativo utilizando JavaFX para a interface gráfica, aplicando conceitos de orientação a objetos e arquitetura MVC (Model-View-Controller).

Clique na imagem abaixo para assistir ao vídeo de demonstração:

[![Jogo da Forca - Demonstração](https://img.youtube.com/vi/362sBdnwerE/0.jpg)](https://youtu.be/362sBdnwerE)

*Clique na imagem para abrir o vídeo no YouTube*

## 1. Descrição do Projeto
O Jogo da Forca é um clássico jogo de adivinhação onde o jogador deve descobrir uma palavra secreta, escolhendo letras do alfabeto dentro de um limite de 6 erros. Este projeto implementa uma versão desktop com interface gráfica moderna, permitindo a seleção de múltiplos temas e oferecendo uma experiência visual atraente e interativa.

### Funcionalidades Principais:
- **Seleção de Múltiplos Temas**: O jogador pode escolher um ou mais temas (animais, frutas, profissões, etc.) para compor o banco de palavras
- **Sistema de Dicas**: Uma dica é exibida quando o jogador atinge 5 erros (de 6 possíveis)
- **Interface Gráfica Completa**: Telas estilizadas com CSS, botões interativos e animações visuais
- **Desenho Progressivo da Forca**: A cada erro, uma parte do boneco é desenhada
- **Banco de Palavras Expansível**: Novos temas e palavras podem ser adicionados via arquivos .txt

## 2. Tecnologias Utilizadas

| Tecnologia | Descrição |
|------------|-----------|
| **Java** | Linguagem de programação principal |
| **JavaFX** | Framework para criação da interface gráfica |
| **Scene Builder** | Ferramenta visual para design das telas (arquivos .fxml) |
| **NetBeans 8.2** | Ambiente de desenvolvimento integrado (IDE) |
| **CSS** | Estilização dos componentes visuais |
| **Arquivos .txt** | Armazenamento dos temas e palavras |


## 3. Compilação e Execução
 1- Clone o repositório:  

```bash
git clone https://github.com/seu-usuario/Jogo-Forca-JavaFX-AEDS.git  
```
2- Abra o projeto no NetBeans  

3- Certifique-se de que o JavaFX está corretamente configurado no classpath  

4- Execute a classe principal (main) do projeto

5- Ou compile via terminal: 
```bash
# Compilação
javac --module-path /caminho/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -d bin src/**/*.java
# Execução
java --module-path /caminho/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp bin Main