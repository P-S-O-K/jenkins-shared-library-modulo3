
# Jenkins Shared Library - Módulo 3 DevOps

Biblioteca compartida de Jenkins para automatizar el proceso
CI/CD del proyecto Backend Módulo 3.

## Tecnologías

- Jenkins
- Groovy
- Node.js
- Docker
- Docker Hub
- GitHub

## Estructura

jenkins-shared-library-modulo3/
├── README.md
└── vars/
    └── backendPipeline.groovy

## Funcionalidades

La función backendPipeline() ejecuta las siguientes etapas:

1. Checkout: obtiene el código fuente desde GitHub.
2. Instalar dependencias: ejecuta npm ci.
3. Validar aplicación: verifica la sintaxis de index.js.
4. Construir imagen Docker: genera la imagen del backend.
5. Publicar en Docker Hub: publica la imagen generada.

## Configuración

La biblioteca se registrará en Jenkins como:

- Nombre: backend-shared-library
- Rama predeterminada: main
- Repositorio:
  https://github.com/P-S-O-K/jenkins-shared-library-modulo3

## Requisitos de ejecución

- Jenkins con soporte para Pipeline Shared Libraries.
- Agente con Node.js, npm y Docker disponibles.
- Credencial Jenkins con ID dockerhub-credentials.
- Permisos para publicar imágenes en Docker Hub.

## Estado

Biblioteca implementada. Integración y ejecución pendientes
de validación en Jenkins.
