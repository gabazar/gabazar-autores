# Gabazar-autores
[![Build](https://github.com/gabazar/gabazar-autores/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/gabazar/gabazar-autores/actions/workflows/build.yml)
[![codecov](https://codecov.io/gh/gabazar/gabazar-autores/branch/main/graph/badge.svg?token=JOIUBJXYJ9)](https://codecov.io/gh/gabazar/gabazar-autores)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=gabazar_gabazar-autores&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=gabazar_gabazar-autores) 
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=gabazar_gabazar-autores&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=gabazar_gabazar-autores) 
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=gabazar_gabazar-autores&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=gabazar_gabazar-autores) 
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=gabazar_gabazar-autores&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=gabazar_gabazar-autores)

## Contexto
Esse serviço faz parte do projeto [Gabazar](https://github.com/gabazar/gabazar), esse serviço atende o dominio de Autores.

## Arquitetura
O serviço utiliza arquitetura hexagonal, todas a integrações externas (controllers, clients, repositories...) estão no pacote de adapters e as regras de negocio estão isoladas no pacote core. 

Contratos externos (ex.: APIs e eventos) não devem ter interação com as regras de negocio, eles devem ser mapeados para uma entidade do dominio e consequentemente enviados para o serviço / caso de uso correspondente.

## Checks

- [X] API CRUD do dominio de Autores
- [X] Validação automatica de campos da API
- [X] Handler de exceptions da API
- [X] Configuração esteira Travis
- [X] Configuração esteira GitHub Actions
- [X] Configuração de cobertura Codecov
- [ ] FeignClient do Livros
- [ ] Implementação de serviço de autenticação
- [ ] Swagger
