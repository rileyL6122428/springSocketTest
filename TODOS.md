## Basic Dev Phase
- [x] Touch up styling on chat
- [x] Smoothen out session glitches
  * check for session ID and don't make a new anonymous user
- [x] Prevent HTML caching
- [x] Show name on matchmaking

- [x] leave room
  *actually leave room*
  *send message in room for leaving chat*
  *Show all users names in the chat*

- [x] emit matchmaking stats on person joining room
- [x] remove all race conditions from subscriptions


## Code Polish Phase
- [ ] Refactor
  * Frontend

    1. *CHECK* use services to pull in all state for component
    2. get rid of service facade, and make messaging service calls more configurable
    3. *CHECK* take matchmaking stat calculation out of matchmaking component
    4. handle domain instantiation by using a third party library or make factories to do it
    5. *CHECK* do a better job of file/module organization
    6. Make file naming consistent
    7. E2E testing
    8. Unit testing

  * Backend

## Styling Phase
- [ ] decent styling (for just chat)

## Unit Testing Phase
- [ ] Unit test
  * Frontend
  * Backend

## Deployment Phase
- [ ] To Docker Image
