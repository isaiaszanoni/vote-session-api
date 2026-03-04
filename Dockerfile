# Build stage
FROM gradle:8.10.2-jdk21 AS build
WORKDIR /app

# Copia arquivos de configuração primeiro (para cache)
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Baixa dependências (cache)
RUN gradle dependencies --no-daemon

# Copia código fonte
COPY src ./src

# Build da aplicação
RUN gradle clean build -x test --no-daemon

# Runtime stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copia o jar gerado
COPY --from=build /app/build/libs/*.jar app.jar

# Railway usa a porta dinâmica via $PORT
EXPOSE 8080

# Healthcheck para Railway (opcional)
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Entrypoint com suporte à porta do Railway
ENTRYPOINT ["sh", "-c", "java -jar -Dserver.port=${PORT:-8080} app.jar"]