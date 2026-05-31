# Simulador de Cinemática Directa e Inversa para un Brazo Robótico en 2D

Este proyecto consiste en el desarrollo de una aplicación en Java (POO) que permite simular el movimiento de un brazo robótico 2D de dos grados de libertad.

La aplicación implementa algoritmos de cinemática directa e inversa para determinar la posición del efector final a partir de los ángulos articulares, 
así como calcular los ángulos a alcanzar una posición dada por el usuario.

Para la resolución de la cinemática inversa se utiliza el método numérico de Newton-Raphson multidimensional.

El programa cuenta con una interfaz gráfica que permite visualizar el movimiento del robot en tiempo real.

## Tecnologías utilizadas
- Java
- Java Swing
- NetBeans IDE
- POO
- Método de Newton-Raphson Multidimensional
- Algebra Lineal
- Cinemática de Robots
  
- ## Instrucciones de uso
1. Abrir el proyecto en NetBeans.
2. Compilar el proyecto.
3. Ejecutar la clase Main.
4. Ingresar las longitudes de los eslabones (aunque ya viene por defecto uno).
5. Ingresar la posicion objetivo (X,Y) (aunque ya viene por defecto uno).
6. Presionar el boton "simular".
7. Observar la animacion del brazo robotico y los resultados obtenidos.
8. Presionar el boton "guardar" para guardar los datos de su brazo robotico en un archivo txt.

## Fundamento matemático
La cinemática directa se calcula mediante:

x = L1 cos(θ1) + L2 cos(θ1 + θ2)

y = L1 sin(θ1) + L2 sin(θ1 + θ2)

La cinemática inversa se resuelve mediante el método iterativo de Newton-Raphson Multidimensional:

θ(k+1) = θ(k) - J⁻¹(θ(k)) F(θ(k))

donde:

- F(θ) representa el error entre la posición actual y la posición deseada.
- J es la matriz Jacobiana del sistema.

## UML del Proyecto


