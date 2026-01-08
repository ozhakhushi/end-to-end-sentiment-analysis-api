import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

class Frontend extends Frame implements ActionListener {

    TextField inputField;
    Label resultLabel;

    public Frontend() {
        setTitle("Sentiment Analyzer tool");
        setSize(600, 300);
        setLayout(new GridBagLayout());
        setBackground(new Color(235, 235, 255));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(15, 15, 15, 15);
        c.fill = GridBagConstraints.HORIZONTAL;

        // Heading
        Label heading = new Label("Sentiment Analysis tool");
        heading.setFont(new Font("Arial", Font.BOLD, 28));
        heading.setForeground(new Color(70, 0, 160));
        heading.setAlignment(Label.CENTER);
        c.gridy = 0;
        c.gridwidth = 2;
        add(heading, c);

        // Input field
        inputField = new TextField(40);
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        c.gridy = 1;
        add(inputField, c);

        // Button
        Button analyzeBtn = new Button("lets Analyze!");
        analyzeBtn.setFont(new Font("Arial", Font.BOLD, 18));
        analyzeBtn.setBackground(new Color(120, 80, 255));
        analyzeBtn.setForeground(Color.white);
        analyzeBtn.addActionListener(this);
        c.gridy = 2;
        add(analyzeBtn, c);

        // Result Label
        resultLabel = new Label("");
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        resultLabel.setForeground(Color.black);
        resultLabel.setAlignment(Label.CENTER);
        c.gridy = 3;
        add(resultLabel, c);

        // Close Window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // -----------------------
    // JSON POST METHOD
    // -----------------------
    public String callAPI(String text) throws Exception {

        URL url = new URL("http://127.0.0.1:8000/predict");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");

        // JSON body
        String jsonBody = "{\"text\":\"" + text.replace("\"", "\\\"") + "\"}";

        OutputStream os = con.getOutputStream();
        os.write(jsonBody.getBytes(StandardCharsets.UTF_8));
        os.flush();
        os.close();

        BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));

        StringBuilder response = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

        // Extract only the sentiment value from JSON safely
        String json = response.toString();
        String clean = json.replace("{", "").replace("}", "")
                .replace("\"sentiment\":", "").replace("\"", "").trim();

        return clean;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = inputField.getText().trim();
        if (text.isEmpty()) {
            resultLabel.setText("Please enter some text!");
            return;
        }

        try {
            String output = callAPI(text);
            resultLabel.setText("Sentiment: " + output);
        } catch (Exception ex) {
            resultLabel.setText("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Frontend();
    }
}
