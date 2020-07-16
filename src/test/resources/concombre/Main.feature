@MainFct
Feature: Stock Management

# BeforeEach in Gherkin
  Background:
    Given I've 2 products
    And I add 3 additional products

    # Simple scenario
    @LegacyScenario
    Scenario: Current stock
      When I count everything I have in stock
      Then I've at least 2 products in stock

    # Loop on values in Examples
    @LegacyScenario
    Scenario Outline: New products
      Given I need to add some <product>
      And I know how much I have
      When I add it to the stock
      Then I should have more than the minimum needed

      @SetOne
      Examples:
      | product |
      | "Ladder" |
      | "Chest" |
      | "Table" |

      @SetTwo
      Examples:
        | product |
        | "Chair" |
        | "Painting" |
        | "Monitor" |

      @SetOne @SetTwo @SetThree
      Examples:
        | product |
        | "Oven" |
        | "Projector" |
        | "Bag" |

    # Scenario with data tables
    @NewScenario
    Scenario: Check if a delivery is possible
      Given today we need to setup a delivery to these clients :
        | fullname           | country  | address              |
        | "Michel Concombre" | "Italy" | "116 rue de la Tour" |
        | "Paul Smith"       | "U.K."   | "Abbey Orchard st"   |
        | "Lily Chester"     | "Spain"   | "Abingdon st"       |
      When only these companies are available :
        | companyName      | country   |
        | "Postal Service" | "U.K."    |
        | "letsGoDelivery" | "U.K."    |
        | "expressDel"     | "Italy"    |
      Then every product can be shipped

