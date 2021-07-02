# ![Logo-template](images/logo-template.png)
# Recurso APX Library PISDR012

>El objetivo de este documento es proveer informacion relacionada de la Libreria PISDR012 que utiliza este proyecto y que ha sido implementado en APX.

### 1.  Funcionalidad

>Esta Librería APX tiene como objetivo realizar las distintas operaciones que se necesitan en la BD.

#### 1.1 Caso de Uso:

> El uso de la Librería PISDR012 está orientado a realizar consultas, inserts, updates en la BD.

### 2. Capacidades:

> Esta **librería** brinda la capacidad a los aplicativos de poder realizar las operaciones necesarias en la BD.

#### 2.1 Método 1: executeInsuranceProduct(Map<String, Object> arguments)
> Método que hace una consulta a la tabla T_PISD_INSURANCE_PRODUCT

##### 2.1.1 Datos de Entrada

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| arguments | Map | Mapa que contiene el nombre y el valor de los filtros de la consulta sql |

##### 2.1.2 Datos de Salida

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| responseInsuranceProduct | Map | Mapa que contiene el valor de los campos de la fila devuelta |

##### 2.1.3 Ejemplo
```java
Map<String, Object> responseInsuranceProduct = pisdR012.executeInsuranceProduct(Map<String, Object> arguments);
```

#### 2.2 Método 2: executeGetProductIdForRimac(Map<String, Object> arguments)
> Método que hace una consulta a la tabla T_PISD_INSURANCE_BUSINESS

##### 2.2.1 Datos de Entrada

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| arguments | Map | Mapa que contiene el nombre y el valor de los filtros de la consulta sql |

##### 2.2.2 Datos de Salida

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| responseInsuranceBusiness | Map | Mapa que contiene el valor de los campos de la fila devuelta |

##### 2.2.3 Ejemplo
```java
Map<String, Object> responseInsuranceBusiness = pisdR012.executeGetProductIdForRimac(Map<String, Object> arguments);
```

#### 2.3 Método 3: executeInsuranceProductModality(Map<String, Object> arguments)
> Método que hace una consulta a la tabla T_PISD_INSRNC_PRD_MODALITY

##### 2.3.1 Datos de Entrada

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| arguments | Map | Mapa que contiene el nombre y el valor de los filtros de la consulta sql |

##### 2.3.2 Datos de Salida

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| responseInsuranceProductModality | Map | Mapa que contiene el listado de campos de las filas devueltas |

##### 2.3.3 Ejemplo
```java
Map<String, Object> responseInsuranceProductModality = pisdR012.executeInsuranceProductModality(Map<String, Object> arguments);
```

#### 2.4 Método 4: executeGetConsiderations(Map<String, Object> arguments)
> Método que hace una consulta a la tabla T_PISD_MODALITY_INSRNC_CNSD

##### 2.4.1 Datos de Entrada

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| arguments | Map | Mapa que contiene el nombre y el valor de los filtros de la consulta sql |

##### 2.4.2 Datos de Salida

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| responseConsiderations | Map | Mapa que contiene el listado de campos de las filas devueltas |

##### 2.4.3 Ejemplo
```java
Map<String, Object> responseConsiderations = pisdR012.executeGetConsiderations(Map<String, Object> arguments);
```

#### 2.5 Método 5: executeGetSimulationId()
> Método que devuelve el último id generado en la tabla de simulación 

##### 2.5.1 Datos de Entrada
   
    Ninguno

##### 2.5.2 Datos de Salida

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| responseGetInsuranceSimulationId | Map | Mapa que contiene el valor del id devuelto |

##### 2.5.3 Ejemplo
```java
Map<String, Object> responseGetInsuranceSimulationId = pisdR012.executeGetSimulationId();
```

#### 2.6 Método 6: executeSaveSimulation(Map<String, Object> arguments)
> Método que inserta un nuevo registro en la tabla T_PISD_INSURANCE_SIMULATION

##### 2.6.1 Datos de Entrada

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| arguments | Map | Mapa que contiene el nombre y el valor de los campos a insertar en la tabla |

##### 2.6.2 Datos de Salida

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| saveSimulationExecuted | Boolean | Determina si el insert se realizó correctamente |

##### 2.6.3 Ejemplo
```java
boolean saveSimulationExecuted = pisdR012.executeSaveSimulation(Map<String, Object> arguments);
```

#### 2.7 Método 7: executeSaveSimulationProduct(Map<String, Object> arguments)
> Método que inserta un nuevo registro en la tabla T_PISD_INSRNC_SIMLT_PRD

##### 2.7.1 Datos de Entrada

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| arguments | Map | Mapa que contiene el nombre y el valor de los campos a insertar en la tabla |

##### 2.7.2 Datos de Salida

    Ninguno

##### 2.7.3 Ejemplo
```java
pisdR012.executeSaveSimulationProduct(Map<String, Object> arguments);
```

#### 2.8 Método 8: executeSaveSimulationVehicle(Map<String, Object> arguments)
> Método que inserta un nuevo registro en la tabla T_PISD_INSRNC_SIMLT_VEHICLE

##### 2.8.1 Datos de Entrada

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| arguments | Map | Mapa que contiene el nombre y el valor de los campos a insertar en la tabla |

##### 2.8.2 Datos de Salida

    Ninguno

##### 2.8.3 Ejemplo
```java
pisdR012.executeSaveSimulationVehicle(Map<String, Object> arguments);
```

#### 2.9 Método 9: executeGetInsuranceQuotation(String quotationId)
> Método que hace una consulta a la tabla T_PISD_INSRNC_QUOTATION_MOD

##### 2.9.1 Datos de Entrada

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| quotationId | String | Código de cotización BBVA |

##### 2.9.2 Datos de Salida

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| response | List | Lista que contiene el valor de los campos de las filas devueltas |

##### 2.9.3 Ejemplo
```java
List<Map<String, Object>> response = pisdR012.executeGetInsuranceQuotation(String quotationId);
```

#### 2.10 Método 10: executeGetInsuranceSimulationIdAndExpiredDate(String quotationId)
> Método que hace una consulta a la tabla T_PISD_INSURANCE_SIMULATION

##### 2.10.1 Datos de Entrada

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| quotationId | String | Código de cotización Rimac |

##### 2.10.2 Datos de Salida

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| responseQueryGetInsuranceSimulationIdAndExpiredDate | Map | Mapa que contiene el valor de los campos devueltos |

##### 2.10.3 Ejemplo
```java
Map<String, Object> responseQueryGetInsuranceSimulationIdAndExpiredDate = pisdR012.executeGetInsuranceSimulationIdAndExpiredDate(String quotationId);
```

#### 2.11 Método 11: executeSaveInsuranceQuotation(Map<String, Object> arguments)
> Método que inserta un nuevo registro a la tabla T_PISD_INSURANCE_QUOTATION

##### 2.11.1 Datos de Entrada

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| arguments | Map | Mapa que contiene el nombre y el valor de los campos a insertar en la tabla |

##### 2.11.2 Datos de Salida

    Ninguno

##### 2.11.3 Ejemplo
```java
pisdR012.executeSaveInsuranceQuotation(Map<String, Object> arguments);
```

#### 2.12 Método 12: executeSaveInsuranceQuotationMod(Map<String, Object> arguments)
> Método que inserta un nuevo registro a la tabla T_PISD_INSRNC_QUOTATION_MOD

##### 2.12.1 Datos de Entrada

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| arguments | Map | Mapa que contiene el nombre y el valor de los campos a insertar en la tabla |

##### 2.12.2 Datos de Salida

    Ninguno

##### 2.12.3 Ejemplo
```java
pisdR012.executeSaveInsuranceQuotationMod(Map<String, Object> arguments);
```

#### 2.13 Método 13: executeSaveInsuranceQuotationVeh(Map<String, Object> arguments)
> Método que inserta un nuevo registro a la tabla T_PISD_INSRNC_QUOTATION_VEH

##### 2.13.1 Datos de Entrada

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| arguments | Map | Mapa que contiene el nombre y el valor de los campos a insertar en la tabla |

##### 2.13.2 Datos de Salida

    Ninguno

##### 2.13.3 Ejemplo
```java
pisdR012.executeSaveInsuranceQuotationVeh(Map<String, Object> arguments);
```

#### 2.14 Método 14: executeGetCompanyDescById(BigDecimal companyId)
> Método que hace una consulta a la tabla T_PISD_INSURANCE_COMPANY

##### 2.14.1 Datos de Entrada

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| companyId | BigDecimal | Id de la compañía aseguradora |

##### 2.14.2 Datos de Salida

|#|Nombre del Atributo|Tipo de Dato| Descripción|
| :----|:---------- |:--------------| :-----|
|1| responseQuerySelectInsuranceCompany | Map | Mapa que contiene el valor de los campos devueltos |

##### 2.14.3 Ejemplo
```java
Map<String, Object> responseQuerySelectInsuranceCompany = pisdR012.executeGetCompanyDescById(BigDecimal companyId);
```

### 3.  Mensajes:

#### 3.1  Código PISD00120000:
> Este código de error es devuelto cuando no se recupera ningún dato de la BD.

#### 3.2 Código PISD00120012:
> Este código de error es devuelto cuando no se recuperan los datos de una cotización.

### 4.  Versiones:
#### 4.1  Versión 0.1.1-SNAPSHOT

+ Versión 0.1.1-SNAPSHOT: Esta versión permite realizar las operaciones necesarias en la BD para las transacciones PISDT001, 002, 003, 004.