import * as React from "react";
import { Grid, Button } from "@mui/material";
import { Avatar } from "@mui/material";
import { ProgressBar } from "react-bootstrap";
import Typography from "@mui/material/Typography";
import AvatarImage from "../../patient1.jpg";

import "../css/style-sheet.css";

import HeaderContainer from "./HeaderContainer";

interface Props {
  name?: string;
  progress?: number;
  reqSession?: number;
  session?: number;
  type?: string;
  date?: string;
}

const NextPatient: React.FC<Props> = (props) => {
  const startSession = () => (window.location.href = "/patient-page");

  return (
    <div className="box-shadow">
      <HeaderContainer title="Sıradaki Hasta" />
      <Grid container direction="row" height="40vh">
        <Grid container item sm={8} direction="column" className="Container">
          <Typography className="NextPatient-Text">{props.date}</Typography>
          <Typography className="NextPatient-Text">{props.type}</Typography>
          <Typography className="NextPatient-Text">
            {props.session}/{props.reqSession}
          </Typography>
          <Typography className="NextPatient-Text">Toplam İyileşme</Typography>

          <ProgressBar
            className="progress"
            now={props.progress}
            label={`${props.progress}%`}
            variant="progress-bg"
            style={{
              width: "22vw",
              height: "4vh",
            }}
          />
        </Grid>
        <Grid
          container
          item
          sm={4}
          style={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "center",
          }}
        >
          <div
            style={{
              margin: "30px 30px 20px 30px",
            }}
          >
            <Avatar
              alt="Hasta 1"
              src={AvatarImage}
              sx={{ width: "10vw", height: "10vw" }}
            />
          </div>
          <div style={{ marginBottom: "30px" }}>
            <Typography className="NextPatient-Name">{props.name}</Typography>
          </div>
        </Grid>
      </Grid>
      <Grid container justifyContent="center">
        <Button
          className="NextPatient-Button"
          variant="contained"
          onClick={startSession}
        >
          Seansı Başlat
        </Button>
      </Grid>
    </div>
  );
};

export default NextPatient;
