🚀 QR Code Generator & AWS S3 Integration Gerador de QR Codes de alta performance com Java 25 e Docker 

📋 Sobre o Projeto:
Este é um microserviço robusto desenvolvido em Java 25 focado na geração dinâmica de QR Codes e no armazenamento automatizado em nuvem utilizando o Amazon S3. O projeto foi totalmente conteinerizado para garantir que o ambiente de desenvolvimento seja idêntico ao de produção.

🛠️ Tecnologias Utilizadas Linguagem:
Java 25 (Eclipse Temurin)
Framework: Spring Boot 4.x
Build Tool: Maven
Cloud: AWS SDK for S3
Infra: Docker & Docker Compose

⚙️ Configuração do Ambiente:
Para garantir a segurança das suas credenciais, o projeto utiliza um arquivo .env. Certifique-se de criá-lo na raiz do projeto (ele já está listado no .gitignore).

Exemplo do arquivo .env:

AWS_ACCESS_KEY_ID=sua_chave_aqui
AWS_SECRET_ACCESS_KEY=seu_segredo_aqui
AWS_REGION=us-east-1
AWS_S3_BUCKET=nome-do-seu-bucket

📦 Como Executar:
O setup foi simplificado para um único comando. Certifique-se de que o você tenha o Docker na sua maquina.

Build e Start:
PowerShell > docker compose up --build 
O sistema estará disponível em: http://localhost:8080

🛡️ Segurança Aviso:
Nunca versione o arquivo .env. As chaves da AWS são injetadas em tempo de execução via Docker Compose para garantir que não fiquem expostas nas camadas da imagem.
