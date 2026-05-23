# Jogo da Velha Android

Projeto Android em Kotlin com Jetpack Compose.

## Como gerar o APK no Android Studio

1. Abra o Android Studio.
2. Selecione **Open** e escolha esta pasta.
3. Aguarde o Gradle sincronizar.
4. Vá em **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
5. O APK será criado em `app/build/outputs/apk/debug/app-debug.apk`.

## Pelo terminal, se você tiver Gradle e Android SDK instalados

```bash
gradle :app:assembleDebug
```

O APK gerado fica em:

```text
app/build/outputs/apk/debug/app-debug.apk
```
