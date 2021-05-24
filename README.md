# Payroll System Project

#### O objetivo do projeto é construir um sistema de folha de pagamento. O sistema consiste do gerenciamento de pagamentos dos empregados de uma empresa. Além disso, o sistema deve gerenciar os dados destes empregados, a exemplo os cartões de pontos. Empregados devem receber o salário no momento correto, usando o método que eles preferem, obedecendo várias taxas e impostos deduzidos do salário.
- Alguns empregados são horistas. Eles recebem um salário por hora trabalhada. Eles submetem "cartões de ponto" todo dia para informar o número de horas trabalhadas naquele dia. Se um empregado trabalhar mais do que 8 horas, deve receber 1.5 a taxa normal urante as horas extras. Eles são pagos toda sexta-feira.

- Alguns empregados recebem um salário fixo mensal. São pagos no último dia útil do mês (desconsidere feriados). Tais empregados são chamados "assalariados".

- Alguns empregados assalariados são comissionados e portanto recebem uma comissão, um percentual das vendas que realizam. Eles submetem resultados de vendas que informam a data e valor da venda. O percentual de comissão varia de empregado para empregado. Eles são pagos a cada 2 sextas-feiras; neste momento, devem receber o equivalente de 2 semanas de salário fixo mais as comissões do período.

* Empregados podem escolher o método de pagamento.
* Podem receber um cheque pelos correios.
* Podem receber um cheque em mãos o Podem pedir depósito em conta bancária.

- Alguns empregados pertencem ao sindicato (para simplificar, só há um possível sindicato). O sindicato cobra uma taxa mensal do empregado e essa taxa pode variar entre empregados. A taxa sindical é deduzida do salário. Além do mais, o sindicato pode ocasionalmente cobrar taxas de serviços adicionais a um empregado. Tais taxas de serviço são submetidas pelo sindicato mensalmente e devem ser deduzidas do próximo contracheque do empregado. A identificação do empregado no sindicato não é a mesma da identificação no sistema de folha de pagamento.

- A folha de pagamento é rodada todo dia e deve pagar os empregados cujos salários vencem naquele dia. O sistema receberá a data até a qual o pagamento deve ser feito e calculará o pagamento para cada empregado desde a última vez em que este foi pago.


# Code Smells

### Long Class:
- Classe EmployeeConf é muito extensa.
- Na classe EmployeeConf existe maneiras de verificar qual o tipo de empregado que possuem uma linguagem implícita (precisando usar comentários para deixar claro).

### Long Parameter List:
- No método editEmployee (na classe EmployeeConf), existem parâmetros para Hourly, Salaried e Commissioned muito longos, usando 5 atributos do objeto Employee.

### Long Method:
- Método editEmployee da classe EmployeeConf muito extensa.

### Duplicated Code:
- Classes SalesReport e ServicesFees possuem a mesma estrutura.

### Unclear:
- Nome dos métodos addSR, addTC, addSF não explica o que o objetivo do método.

# Refactor

### Template Method
"O padrão do *Template Method* sugere que você quebre um algoritmo em uma série de etapas, transforme essas etapas em métodos, e coloque uma série de chamadas para esses métodos dentro de um único método padrão." Na classe EmployeeConf existia um método chamado editEmployee, o qual era muito extenso e possuia diversos switch/case, que acabavam deixando o código confuso. Então, foi utilizado o padrão Template Method, criando métodos que separassem as etapas de editEmployee.
Unindo isso ao bad smell "Long Method", também associado ao método editEmployee, foi criada uma classe exclusiva para essa função de editar um empregado.
* [antes](https://github.com/gabrielalimact/payroll-project/blob/74c0b19f7a61a498b19599d39e15a56d6ba31122/src/app/EmployeeConf.java#L177), [depois](https://github.com/gabrielalimact/payrollProject-codeSmells/blob/master/src/app/employeeMenu/EditEmployeeInfos.java).


### Interpreter
O padrão *Interpreter* foi utilizado para resolver o problema de linguagem implícita na maneira de filtrar os tipos de empregados nos métodos [addTC](https://github.com/gabrielalimact/payroll-project/blob/74c0b19f7a61a498b19599d39e15a56d6ba31122/src/app/EmployeeConf.java#L98), [addSR](https://github.com/gabrielalimact/payroll-project/blob/74c0b19f7a61a498b19599d39e15a56d6ba31122/src/app/EmployeeConf.java#L128) e [addSF](https://github.com/gabrielalimact/payroll-project/blob/74c0b19f7a61a498b19599d39e15a56d6ba31122/src/app/EmployeeConf.java#L155). Para isso, foi criada uma nova classe [EmployeeType](https://github.com/gabrielalimact/payrollProject-codeSmells/blob/129234a4e5a60e3ec7b751156a9d358c5b6706f9/src/app/employeeMenu/EmployeeType.java#L9), dessa forma fica melhor de entender o que tá sendo feito para separar o empregado de acordo com o tipo 'hourly', 'salaried', 'commissioned' e se faz parte ou não do sindicato.

### Move Method
O método getIndiceDaLista(), que ficava na classe [EmployeeConf](https://github.com/gabrielalimact/payroll-project/blob/74c0b19f7a61a498b19599d39e15a56d6ba31122/src/app/EmployeeConf.java#L71) foi movido para a classe [SystemInputs](https://github.com/gabrielalimact/payrollProject-codeSmells/blob/8f8ab781404a43dde97f329ddbded990de7edca4/src/app/SystemInputs.java#L11).


### Outros arranjos

Os mesmos métodos abaixo sofreram alteração em seu nome, com propósito de deixar mais explícito o que cada um faz.
* addTC --> [addTimeCard](https://github.com/gabrielalimact/payrollProject-codeSmells/blob/129234a4e5a60e3ec7b751156a9d358c5b6706f9/src/app/employeeMenu/EmployeeConf.java#L83);
* addSR --> [addSaleReport](https://github.com/gabrielalimact/payrollProject-codeSmells/blob/129234a4e5a60e3ec7b751156a9d358c5b6706f9/src/app/employeeMenu/EmployeeConf.java#L104);
* addSF --> [addServiceFee](https://github.com/gabrielalimact/payrollProject-codeSmells/blob/129234a4e5a60e3ec7b751156a9d358c5b6706f9/src/app/employeeMenu/EmployeeConf.java#L116);


Para resolver o problema 'Long Class' em EmployeeConf, foi criada outra classe exclusiva para o método editEmployee.
* [antes](https://github.com/gabrielalimact/payroll-project/blob/74c0b19f7a61a498b19599d39e15a56d6ba31122/src/app/EmployeeConf.java#L177), [depois](https://github.com/gabrielalimact/payrollProject-codeSmells/blob/master/src/app/EmployeeConf.java).


O bad smell "Long Parameter List" no momento de mudar o tipo de empregado foi resolvido criando outros construtores nas classes [Salaried](https://github.com/gabrielalimact/payrollProject-codeSmells/blob/5c461c0546ce26511c2d79802b63898dc6c29304/src/model/employees/Salaried.java#L11), [Commissioned](https://github.com/gabrielalimact/payrollProject-codeSmells/blob/5c461c0546ce26511c2d79802b63898dc6c29304/src/model/employees/Commissioned.java#L23) e [Hourly](https://github.com/gabrielalimact/payrollProject-codeSmells/blob/5c461c0546ce26511c2d79802b63898dc6c29304/src/model/employees/Hourly.java#L15), que só recebem os parâmetros exclusivos de cada classe.
* [antes](https://github.com/gabrielalimact/payroll-project/blob/74c0b19f7a61a498b19599d39e15a56d6ba31122/src/app/EmployeeConf.java#L215), [depois](https://github.com/gabrielalimact/payrollProject-codeSmells/blob/5c461c0546ce26511c2d79802b63898dc6c29304/src/app/employeeMenu/EditEmployeeInfos.java#L102).
