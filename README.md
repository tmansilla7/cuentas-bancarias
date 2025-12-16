# Banco 

## 📌 Descripción general

El objetivo del trabajo fue desarrollar un **sistema bancario** utilizando **Java**, aplicando **Programación Orientada a Objetos**, **polimorfismo**, **herencia**, **manejo de excepciones** y la metodología **TDD (Test Driven Development)**, cumpliendo con todos los requisitos funcionales definidos en el enunciado.

---

## 🎯 Objetivo del sistema

Modelar el funcionamiento de un banco que gestiona:

- Clientes
- Cuentas bancarias
- Operaciones financieras (depósitos, extracciones y transferencias)

El sistema permite validar reglas específicas según el tipo de cuenta, manejar errores mediante excepciones y obtener distintos reportes ordenados.

---

## 🛠️ Tecnologías utilizadas

- **Java**
- **JUnit** (Testing)
- **Eclipse IDE**
- Proyecto Java tradicional con estructura estándar de paquetes

---

## 🧠 Diseño y conceptos aplicados

### Programación Orientada a Objetos

El diseño del sistema se basó en:

- **Herencia**: una clase base `Cuenta` y especializaciones según el tipo.
- **Polimorfismo**: cada tipo de cuenta redefine el comportamiento de extracción.
- **Encapsulamiento**: atributos privados y acceso controlado mediante métodos.
- **Excepciones**: manejo de situaciones inválidas del dominio bancario.

---

## 🏦 Modelo del dominio

### Banco
- Administra clientes y cuentas
- Permite:
  - Alta de clientes
  - Alta de cuentas
  - Depósitos
  - Extracciones
  - Transferencias
- Devuelve listados ordenados según distintos criterios

### Cliente
- Identificado por **DNI**
- Puede poseer **una o más cuentas**
- El orden natural de los clientes es por **DNI**

### Cuenta bancaria (Clase abstracta)
- Identificada por **CBU**
- Posee saldo y DNI del titular
- Define comportamiento común para:
  - Depositar
  - Extraer (polimórfico)
- El orden natural de las cuentas es por **CBU**

---

## 💳 Tipos de cuentas implementadas

### ✔ Cuenta Sueldo
- Permite extracciones solo si el saldo es suficiente
- No admite descubierto

### ✔ Caja de Ahorros
- Permite hasta **5 extracciones sin costo**
- A partir de la **sexta extracción**, se cobra un recargo fijo de **$100**
- Si no hay saldo suficiente, lanza excepción

### ✔ Cuenta Corriente
- Permite girar en **descubierto**
- Tiene un límite de descubierto configurable
- Cobra una comisión del **5% sobre el monto utilizado en descubierto**
- El saldo puede quedar negativo (deuda con el banco)

---

## ⚠️ Manejo de excepciones

El sistema lanza excepciones en los siguientes casos:

- Intentar extraer dinero sin saldo suficiente
- Intentar dar de alta una cuenta a un cliente inexistente
- Buscar una cuenta con un CBU inválido
- Superar las condiciones permitidas por cada tipo de cuenta

---

## 📊 Reportes implementados

El banco puede devolver:

- 📋 Listado de clientes ordenados por **DNI**
- 💰 Listado de todas las cuentas ordenadas por **saldo**
- 💳 Listado de cuentas corrientes ordenadas por **saldo**
- 🔴 Listado de cuentas corrientes deudoras ordenadas por **saldo deudor**

---

## 🧪 Testing (TDD)

El desarrollo se realizó utilizando **Test Driven Development**, asegurando que el sistema cumpla con los casos de prueba definidos por la cátedra.

Se implementaron tests que validan, entre otros:

- Extracciones válidas e inválidas
- Cálculo correcto de recargos y comisiones
- Manejo de excepciones
- Ordenamientos solicitados

El proyecto cumple con los criterios necesarios para alcanzar los porcentajes de aprobación/promoción establecidos.

---

## 📁 Organización del proyecto

- Paquetes organizados por responsabilidad
- Clases con una única responsabilidad
- Métodos cortos y claros
- Nomenclatura correcta:
  - `UpperCamelCase` para clases
  - `lowerCamelCase` para métodos y variables

---

## ✅ Estado del proyecto

✔ Compila correctamente  
✔ Cumple los requisitos funcionales del enunciado  
✔ Aplica OOP, herencia y polimorfismo  
✔ Manejo correcto de excepciones  
✔ Tests implementados según consignas  

