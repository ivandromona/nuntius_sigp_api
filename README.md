# nuntius_sigp_api
Sistema Integrado De Gestão processual

# Nuntius Backend API RESTfull Service
Sistema Integrado de Gestão Processual Nuntius

java -jar multitenant.jar

Create a file /etc/systemd/system/nuntius.service and add the following contents:

[Unit]
Description= My Spring Project
[Service]
User=Administrator
Group=ubuntu
Type=simple
ExecStart=/usr/bin/java -jar /home/Administrator/nuntius_sigp_api/latest/multitenant-1.0.0.RELEASE.jar
SuccessExitStatus=143
[Install]
WantedBy=multi-user.target

Next, add the following to ensure the service starts on boot
systemctl enable nuntius
Start the service
systemctl start nuntius

Tip: If the service is not running, confirm using:
sudo systemctl status app
And check detailed errors using:
journalctl -u nuntius.service --no-pager
In case journalctl reports an error in the service description file (nuntius.service), change it, and reload systemctl daemon before restarting service
systemctl daemon-reload
systemctl restart nuntius
