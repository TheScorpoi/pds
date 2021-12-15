Para uma melhor implementação deveriamos ter criado um AbstractCakeBuilder, onde essa classe abstrata ia implementar a interface, depois seria so criar as classes ChocolateCakeBuilder
extends AbstractCakeBuilder e o resto seria o mesmo...

Na classe CakeMaster, no createCake, para nao estar sempre a repetir codigo, deveriamos criar uma funcao mais geral, e depois para as outras, chamar essa função e acrescentar o que deveria
ser acrescentado.

O PADRÃO SERIA:
                                CakeMaster --------------------------   AbstractCakeBuilder   -----implements ----- Interface

                                                                          /              \
                                                                         /                \
                                                                        /                  \
                                                                        |                  |
                                                                        |                  |
                                                                        |                  |
                                                                concreteBuilder     ConcreteBuilder