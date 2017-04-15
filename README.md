# LatestPhotoGallery
Se trato de simular el control que usa facebook, twitter y otras redes sociales para mostrar las ultimas fotos agredas al dispositivo, ya sea enviada por alguna red social, descargadas o tomada desde la camara.

- Numero de Imagenes a mostrar Customizable.
- Posibilidad de seleccionar varias imagenes.
- Funcion para obtener las imagenes seleccionadas ( los paths ), asi el usuario puede realizar con ellas lo que ocupe.


![Imagen 1](https://extraimage.net/images/2017/04/15/12c885b175616669fb5bbeeff9cb814a.jpg)
![Imagen 2](https://extraimage.net/images/2017/04/15/2f61317d2abf97637d96f9eaad10b9b7.jpg)

# Instalación
Agregar en el build.gradle del folder root.

```java
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Agregar la referencia en el build.gradle del app/

```java
dependencies {
   compile 'com.github.oswaldo89:LatestPhotoGallery:1.0.1'
}
```

Despues Agregamos el control a nuestra vista

```xml
<com.oswaldogh89.library.LatestImages
      android:id="@+id/customControl"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      last:quantity="15">

  </com.oswaldogh89.library.LatestImages>
```

El siguiente parametro indica cuantas imagenes se visualizaran.
```xml
last:quantity="15
```

NOTA: En el proyecto, puede verse un ejemplo de su implementacion.


