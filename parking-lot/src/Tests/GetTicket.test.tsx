import React from 'react';

import {configure, shallow } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import GetTicket from '../components/GetTicket';

configure({adapter: new Adapter()});

describe("GetTicket component testing", () => {
    let wrapper:any;
    beforeEach(() => {
        wrapper = shallow(<GetTicket />);
    })
    it("should contain an button with text Get Ticket", () => {
        const button = wrapper.find(".getTicketButton");
        const text = button.text();
        expect(text).toBe("Get Ticket");         
    });

    it("should have a input box for vehicle number", () => {
        expect(wrapper.exists("input")).toBe(true);         
    });


    it('Should reflect vehicle number when user types it', () => {
        const input = wrapper.find("input");
        input.simulate('change', { currentTarget: { value:"MH 39 4625" } });
        wrapper.update();
        expect(wrapper.state("vehicleNo")).toBe("MH 39 4625");
      });
});
