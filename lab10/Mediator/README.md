# PDS_2021_403 - Lab10
## Mediator

# 1 - O problema
Desenvolver um programa onde é preciso fazer uma gestão sobre um aeroporto com os voos que podem aterrar e/ou levantar voo.

Um **Flight** tem um iD (*String*), e uma data (*String*), o voo pode aterrar e levantar voo, porém só o poderá fazer dependendo de uma autorização da torre de controlo.

Uma **Runway** tem um nome (*String*), um iD (*String*), e um compromento em km (*double*), é aqui que os aviões vão poder pousar ou levantar voo, porém so o fazem também dependendo de uma autorização prévia da torre de controlo.

# 2 - A solução

O padrão utilizado foi o **Mediator**. Criamos a *interface* **MediatorInterface** que tem as funções que a torre de controlo (aqui é o nosso mediator) vai usar, registar a pista, o voos, ver se a pista está livre para que aviões aterrem ou que levantem voo, sendo que também tem uma função que permite à torre mudar o estado da pista ou seja, dar ou não autorização se a mesma pode ser usada por um avião. A classe Mediator, a nossa torre de contolo, implementa a interface (**MediatorInterface**). 
Temos a interface **Option** que tem duas funções, o land e o takeOff, estas funções vão ser depois implementadas pelo **Flight** e pela **Runway**.
Como é o **Flight** que quer aterrar e levantar voo, é nele que as funções *land* e *takeOff* vao fazer os pedidos à torre para aterrar ou levantar voo, respetivamente. Podendo este pedido ser recusado ou não, dependendo de a pistar estar em uso ou não.
Na main, que está na classe **MediatorDemo**, tem um *Disclaimer* a avisar que para uma melhor implementação o programa devia ser desenvolvivo com *Threads* para simular mais que um avião ao mesmo tempo a querer aterrar ou levantar voo, porém por questões de tempo decidimos deixar estático, não é tão bom para mostrar as verdadeira funcionalidades do mediator mas dá para ter uma ideia. Para simular que a pista ficou indisponível simulamos que um avião caiu, e desse modo com hardcoded a torre muda o estado da pista e coloca-a como indisponível.

# 3 - Fontes utilizadas
1: Slides das Aulas Teóricas (e@Learning) 
2: https://github.com/iluwatar/java-design-patterns/tree/master/mediator
3: https://refactoring.guru/design-patterns/mediator
4: https://www.baeldung.com/java-mediator-pattern

