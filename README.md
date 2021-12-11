# Reto_Cucumber
Lo primero es crear un proyecto en Intellij para realizar la automatización de buscar un producto, se selecciona Gradle y se deja todo igual y se le da siguiente.

![image](https://user-images.githubusercontent.com/86448944/145666236-87e86a94-d8e7-47a6-9608-d9be274f2cd3.png)

Una vez cread el proyecto, en er archivo build.gradle se egragan las siguientes dependencias, que son para manejar el serenity y el excel.

    implementation 'net.serenity-bdd:serenity-junit:2.0.80'
    implementation 'net.serenity-bdd:serenity-cucumber:1.9.45'
    implementation 'net.serenity-bdd:serenity-core:2.0.80'
    implementation 'org.slf4j:slf4j-simple:1.7.7'
    implementation group: 'org.apache.poi', name: 'poi', version: '3.17'
    implementation group: 'org.apache.poi', name: 'poi-ooxml', version: '3.17'

![image](https://user-images.githubusercontent.com/86448944/145666389-ed0c783a-99b2-4ea7-a410-3bac987a07cf.png)

En la parte superior derecha sale un elefante, se le da clic para que carguen las dependencias

![image](https://user-images.githubusercontent.com/86448944/145666518-b1fb0ef0-5d5e-473e-8279-1c952006e384.png)

Una vez que haya cargado se puede ver la estructura de las carpetas asi:
![image](https://user-images.githubusercontent.com/86448944/145666536-904a9882-6e96-4bb2-a18d-477b7a4e224d.png)

Se empiezan a crear los paquetes (carpetas) con la estructura POM

