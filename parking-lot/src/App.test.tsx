import React from 'react';
import App from './App';

import {configure, shallow } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import ParkingData from './components/ParkingData';
import renderer from 'react-test-renderer';

configure({adapter: new Adapter()});

describe("App component testing", () => {
  
  it("should render header of the page", () => {
    const wrapper = shallow(<App />); 
    const header = wrapper.find("h1");
    const text = header.text();
    expect(text).toBe("Parking Lot");
  });

  it("should contain Parking data component", () => {
    const wrapper = shallow(<App />);
    expect(wrapper.contains(<ParkingData />)).toBe(true);
  })

  it("renders correctly", () => {
    const tree = renderer.create(<App />).toJSON();
    expect(tree).toMatchSnapshot();
  });
});
