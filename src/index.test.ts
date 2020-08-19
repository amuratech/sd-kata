import { Index } from './index'

describe('Index', () => {
  it('should test the setup', () => {
    expect(new Index().testSetup()).toEqual('its Working!!!');
  });
});
