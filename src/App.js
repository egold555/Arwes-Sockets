import React from "react";
import ReactDOM from "react-dom";

import {
  Arwes,
  Words,
  Heading,
  Paragraph,
  Link,
  Blockquote,
  List,
  Content,
  Frame,
  Button,
  Line,
  Loading,
  Image,
  Code,
  Table,
  Project,
  Header,
  Footer,
  Grid,
  Animation,
  Appear,
  Highlight,
  Puffs,
  Logo,
  ThemeProvider,
  withStyles,
  createTheme,
  SoundsProvider,
  withSounds,
  createSounds,
  createLoader,
  createResponsive,
  createPlayer
} from "arwes";

var text = "<h2>Waiting for server....</h2>";

var ws = new WebSocket("ws://localhost:4444");

ws.onopen = function() {
  console.log("Test Message");
};

ws.onmessage = function(evt) {
  var received_msg = evt.data;
  console.log("Recieved: " + received_msg);
  text = received_msg;
  //Somehow update
};

function createMarkup() {
  return { __html: text };
}

class TheApp extends React.Component {
  componentDidMount() {
    setInterval(() => {
      this.setState(() => {
        console.log("setting state");
        return { unseen: "does not display" };
      });
    }, 1000);
  }

  render() {
    console.log("render called");
    return (
      <ThemeProvider theme={createTheme()}>
        <div dangerouslySetInnerHTML={createMarkup()} />
      </ThemeProvider>
    );
  }
}

const AppBG = () => (
  <ThemeProvider theme={createTheme()}>
    <Arwes
      animate
      background="/static/img/background.jpg"
      pattern="/static/img/glow.png"
    >
      <TheApp />
    </Arwes>
  </ThemeProvider>
);

ReactDOM.render(<AppBG />, document.querySelector("#root"));

export default AppBG;
