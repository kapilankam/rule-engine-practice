INSERT INTO rules
(rule_namespace , rule_id, condition,
 action, priority, description)
VALUES (
           'LOAN',
           '1',
           'input.monthlySalary >= 50000 && input.creditScore >= 800 && input.requestedLoanAmount < 4000000 && $(bank.target_done) == false',
           'output.setApprovalStatus(true);output.setSanctionedPercentage(90);output.setProcessingFees(8000);',
           '1',
           'A person is eligible for Home loan?'
       );