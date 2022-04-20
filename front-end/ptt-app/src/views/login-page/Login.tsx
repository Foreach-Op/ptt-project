import React from "react";
import { useNavigate } from "react-router-dom";
import { Button, Grid, TextField, InputAdornment } from "@mui/material";
import { AccountCircle, LockRounded } from "@mui/icons-material";
import "./Login.css";

function Login() {
  const navigate = useNavigate();

  const login = () => {
    // This will navigate to second component
    navigate("/doctor-page");
  };

  return (
    <div className="App">
      <Grid container style={{ minHeight: "100vh" }}>
        <Grid item xs={12} sm={6} md={7}>
          <img
            className="Login-Image"
            src="https://www.care2curephysiotherapy.com/wp-content/uploads/2019/09/physiotherapy_techniques.jpg"
            alt="login-image"
          />
        </Grid>
        <Grid
          container
          item
          xs={12}
          sm={6}
          md={5}
          alignItems="center"
          direction="column"
          justifyContent="space-between"
        >
          <div />
          <div className="Login-Container" style={{ justifyContent: "center" }}>
            <header className="Header">Hesabınıza Giriş Yapın</header>
            <TextField
              margin="normal"
              label="Kullanıcı Adı / e-mail"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <AccountCircle />
                  </InputAdornment>
                ),
              }}
            />
            <TextField
              margin="normal"
              type="password"
              label="Şifre"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <LockRounded />
                  </InputAdornment>
                ),
              }}
            />
            <a
              className="Forgot-Password"
              href="https://google.com"
              target="_blank"
              rel="noopener noreferrer"
            >
              Şifremi Unuttum
            </a>
            <div style={{ height: 40 }} />
            <Button
              className="Button"
              style={{
                borderRadius: 10,
                backgroundColor: "#4EC6C7",
                padding: "8px",
                fontSize: "18px",
                textTransform: "none",
                minWidth: "250px",
                maxWidth: "400px",
                alignSelf: "center",
              }}
              variant="contained"
              onClick={login}
            >
              Giriş Yap
            </Button>
          </div>
          <div />
        </Grid>
      </Grid>
    </div>
  );
}

export default Login;
