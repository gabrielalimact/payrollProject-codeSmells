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
- Método editEmployee da classe EmployeeConf muito extensa com vários switch/case.
- Muitas variáveis locais na classe EmployeeConf.

### Duplicated Code:
- Classes SalesReport e ServicesFees possuem a mesma estrutura.

### Unclear:
- Nome dos métodos addSR, addTC, addSF não explica o que o objetivo do método.

# Refactor

Para melhorar a maneira de verificar o tipo de empregado na classe EmployeeConf, foi criada uma nova classe EmployeeType que possue filtros para cada tipo de empregado e retorna uma lista somente com o tipo desejado, deixando o código mais limpo e claro.
* antiga forma de verificar qual tipo de empregado se encontrava nos métodos [addTC](https://github.com/gabrielalimact/payroll-project/blob/74c0b19f7a61a498b19599d39e15a56d6ba31122/src/app/EmployeeConf.java#L98), [addSR](https://github.com/gabrielalimact/payroll-project/blob/74c0b19f7a61a498b19599d39e15a56d6ba31122/src/app/EmployeeConf.java#L128) e [addSF](https://github.com/gabrielalimact/payroll-project/blob/74c0b19f7a61a498b19599d39e15a56d6ba31122/src/app/EmployeeConf.java#L155). 
* nova forma de verificar fica na classe [EmployeeType](https://github.com/gabrielalimact/payrollProject-codeSmells/blob/8f8ab781404a43dde97f329ddbded990de7edca4/src/app/EmployeeType.java#L7). 

Os mesmos métodos citados acima também sofreram alteração em seu nome.
* addTC --> addTimeCard;
* addSR --> addSaleReport;
* addSF --> addServiceFee;

Para resolver o problema Long Class em EmployeeConf, foi criada outra classe exclusiva para o método editEmployee. E como esse método também tinha um bad smells Long Method, foi reorganizado de uma forma que ficasse mais fácil de enteder cada objetivo.
* [antes](https://github.com/gabrielalimact/payroll-project/blob/74c0b19f7a61a498b19599d39e15a56d6ba31122/src/app/EmployeeConf.java#L177), [depois](https://github.com/gabrielalimact/payrollProject-codeSmells/blob/054fcf19f456a7ad34f10afa8327e07ff2c32bfd/src/app/EditEmployeeInfos.java#L14).
Também foi movido o método [getIndiceDaLista](https://github.com/gabrielalimact/payroll-project/blob/74c0b19f7a61a498b19599d39e15a56d6ba31122/src/app/EmployeeConf.java#L71) para a classe [SystemInputs](https://github.com/gabrielalimact/payrollProject-codeSmells/blob/8f8ab781404a43dde97f329ddbded990de7edca4/src/app/SystemInputs.java#L11).
