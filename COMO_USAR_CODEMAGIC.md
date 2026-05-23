# Como gerar o APK no Codemagic

Este projeto já vem com o arquivo `codemagic.yaml` na raiz.

## Passos

1. Extraia este ZIP no seu computador ou celular.
2. Crie um repositório no GitHub, GitLab ou Bitbucket.
3. Envie todos os arquivos deste projeto para o repositório.
4. No Codemagic, clique em **Add application**.
5. Escolha o provedor do repositório, por exemplo **GitHub**.
6. Selecione o repositório do app.
7. Escolha o tipo de projeto **Android App** ou **Other** se aparecer.
8. Finalize com **Finish: Add application**.
9. Abra o app no Codemagic e clique em **Check for configuration file** se necessário.
10. Escolha o workflow **Android Debug APK**.
11. Clique em **Start new build**.
12. Quando terminar, baixe o APK em **Artifacts**.

O APK gerado deve aparecer como `app-debug.apk`.

## Instalar no celular

Envie o APK para o celular, toque no arquivo e permita instalar apps desconhecidos para o navegador ou gerenciador de arquivos usado.

Este APK é de debug. Serve para testar no seu celular, mas não é o ideal para publicar na Google Play.
