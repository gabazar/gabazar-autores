# Gabazar-autores
[![Build Status](https://app.travis-ci.com/gabazar/gabazar-autores.svg?branch=main)](https://app.travis-ci.com/gabazar/gabazar-autores)
[![codecov](https://codecov.io/gh/gabazar/gabazar-autores/branch/main/graph/badge.svg?token=JOIUBJXYJ9)](https://codecov.io/gh/gabazar/gabazar-autores)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Contexto
Esse serviço faz parte do projeto [Gabazar](https://github.com/gabazar/gabazar), esse serviço atende o dominio de Autores.

## Arquitetura
O serviço utiliza arquitetura hexagonal, todas a integrações externas (controllers, clients, repositories...) estão no pacote de adapters e as regras de negocio estão isoladas no pacote core. 

Contratos externos (ex.: APIs e eventos) não devem ter interação com as regras de negocio, eles devem ser mapeados para uma entidade do dominio e consequentemente enviados para o serviço / caso de uso correspondente.
