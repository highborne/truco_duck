# 🦆 Truco Duck

Aplicativo Android para marcação de pontos em partidas de Truco.

O Truco Duck foi desenvolvido para facilitar o controle de pontuação durante partidas, permitindo registrar vitórias, acompanhar rodadas e consultar o histórico de jogos disputados.

---

## 📱 Funcionalidades

### ✅ Controle de Pontuação
- Adição rápida de pontos:
  - +1
  - +3
  - +6
  - +9
  - +12
- Atualização instantânea do placar.

### 🏆 Controle de Rodadas
- Contabiliza automaticamente as vitórias de cada jogador.
- Exibe a quantidade de rodadas vencidas durante a sessão.

### 📜 Histórico de Partidas
- Armazena os resultados localmente no dispositivo.
- Consulta das partidas anteriores.
- Registro de:
  - vencedor
  - perdedor
  - pontuação final
  - data e hora da partida

### 🔄 Reinício de Jogo
- Reinicia o placar atual sem perder o histórico salvo.

### 👥 Configuração de Jogadores
- Definição dos nomes antes do início da partida.
- Alteração dos participantes a qualquer momento.

---

## 🏗️ Arquitetura

O projeto utiliza uma estrutura simples baseada em Activities.

### Activities

| Activity | Responsabilidade |
|-----------|-----------------|
| StartActivity | Tela inicial |
| SetPlayersActivity | Cadastro dos jogadores |
| MainActivity | Controle do jogo e pontuação |
| HistoryActivity | Exibição do histórico |

---

## 💾 Persistência de Dados

Os resultados são armazenados localmente através de um arquivo JSON.

### Classe responsável

```kotlin
GameRepository
```

### Arquivo gerado

```text
game_history.json
```

### Biblioteca utilizada

```gradle
com.google.code.gson:gson:2.10.1
```

---

## 📦 Estrutura do Projeto

```text
app
 ├── src
 │   ├── main
 │   │   ├── java/com/esomakers/trucoduck
 │   │   │   ├── StartActivity.kt
 │   │   │   ├── SetPlayersActivity.kt
 │   │   │   ├── MainActivity.kt
 │   │   │   ├── HistoryActivity.kt
 │   │   │   └── GameRepository.kt
 │   │   │
 │   │   ├── res
 │   │   │   ├── layout
 │   │   │   ├── drawable
 │   │   │   └── mipmap
 │   │   │
 │   │   └── AndroidManifest.xml
```

---

## 🚀 Tecnologias Utilizadas

- Kotlin
- Android SDK
- AndroidX
- Material Design Components
- Gson

---

## 📋 Requisitos

| Item | Versão |
|--------|---------|
| Android Studio | Hedgehog ou superior |
| Android SDK | 36 |
| Min SDK | 24 |
| Java | 11 |
| Kotlin | Compatível com Android Gradle atual |

---

## ⚙️ Como Executar

### 1. Clonar o repositório

```bash
git clone https://github.com/highborne/truco_duck.git
```

### 2. Abrir no Android Studio

```text
File → Open
```

Selecione a pasta do projeto.

### 3. Sincronizar o Gradle

O Android Studio fará o download automático das dependências.

### 4. Executar

Conecte um dispositivo Android ou utilize um emulador e clique em:

```text
Run ▶
```

---

## 🎮 Como Utilizar

1. Abra o aplicativo.
2. Clique em **Iniciar**.
3. Informe os nomes dos dois jogadores.
4. Inicie a partida.
5. Utilize os botões de pontuação para registrar os pontos.
6. Ao atingir 12 pontos:
   - o vencedor é identificado automaticamente;
   - o resultado é salvo no histórico;
   - uma janela com o resultado é exibida.

---

## 🧠 Modelo de Dados

### PlayerResult

```kotlin
data class PlayerResult(
    val name: String,
    var points: Int,
    var winNumber: Int,
    val playerPosition: Int
)
```

### GameResult

```kotlin
data class GameResult(
    var winner: PlayerResult,
    var loser: PlayerResult,
    var gameDate: String
)
```

---

## 🔮 Melhorias Futuras

- [ ] Modo dupla (2x2)
- [ ] Estatísticas detalhadas
- [ ] Exportação de histórico
- [ ] Backup em nuvem
- [ ] Tema escuro
- [ ] Ranking de jogadores
- [ ] Histórico filtrável
- [ ] Internacionalização

---

## 📄 Licença

Este projeto está disponível para uso e modificação conforme a licença definida pelo autor do repositório.

---

## 👨‍💻 Autor

Desenvolvido por **Everton Oliveira**.

Projeto criado para simplificar a marcação de pontos em partidas de Truco de forma rápida e prática.
