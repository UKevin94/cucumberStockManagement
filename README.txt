Ce projet est en Cucumber/Gherkin et peut être exécuté sans interface graphique avec un lien TM-TF.
Le .feature est en anglais, pour une version française se reporter à l'autre projet disponible.
Les principaux mots-clés ont été inclus à l'exception du "Rule" qui a été ajouté dans Gherkin 6
et les DocStrings qui ne sont pas accepté dans cette version de Cucumber/Gherkin.

Feature : Seul le plan du scénario avec l'élément "Table" a une assertionFailure. Les autres sont des tests OK.

@MainFct
Feature: Stock Management

# BeforeEach in Gherkin
  Background:
    Given I've 2 products
    And I add 3 additional products

    # Simple scenario
    Scenario: Current stock
      When I count everything I have in stock
      Then I've at least 2 products in stock

    # Loop on values in Examples
    Scenario Outline: New products
      Given I need to add some <product>
      And I know how much I have
      When I add it to the stock
      Then I should have more than the minimum needed

      Examples:
      | product |
      | "Ladder" |
      | "Chest" |
      | "Table" |

Technologies :

JUnit 4
Cucumber 4
Dépendance Squash TF Gherkin plugin 1.2.0-RELEASE
