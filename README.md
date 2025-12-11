---
page_type: sample
languages:
- java
- typescript
- html
products:
- github
urlFragment: agent-java-banking-assistant
name: IntelliBank
description: A Multi Agent Banking Assistant with Java and Langchain4j
---

<div align="center">

<img src="robot_redthinking.PNG" alt="AI Agents" width="300"/>


# IntelliBank - A Multi Agent Banking Assistant

[![Open project in GitHub Codespaces](https://img.shields.io/badge/Codespaces-Open-blue?style=flat-square&logo=github)](https://codespaces.new/azure-samples/agent-openai-java-banking-assistant?hide_repo_select=true&ref=main&quickstart=true)
[![Build Status](https://img.shields.io/github/actions/workflow/status/azure-samples/agent-openai-java-banking-assistant/azure-dev.yaml?style=flat-square&label=Build)](https://github.com/azure-samples/agent-openai-java-banking-assistant/actions)
![Java version](https://img.shields.io/badge/Java->=17-3c873a?style=flat-square)

</div>

---
> ⚙️ **Note:**  
> This project runs locally using **Ollama LLaMA 3**.  
> Azure SDK dependencies can included instead of Ollama LLaMA 3. You can use them to connect to Azure's cloud services for response generation.
---

## Overview

This project showcases a banking personal assistant with a conversational interface, allowing users to:
- Check account balances
- Review recent transactions
- Initiate payments

It uses a multi-agent architecture where each agent specializes in handling a particular task, integrated with existing REST APIs. Invoice samples are provided to enable the payments feature using invoice scanning and OCR.

---

## Features

- Modular multi-agent architecture for domain-specific task handling  
- API exposure as internal tools using Spring Boot services  
- Agent invocation via [Langchain4j]  
- React-based single-page chat interface with image upload support (e.g., invoices, bills)  
- Invoice OCR (you may use any open-source or self-hosted OCR tool) 
---

## Architecture

The personal banking assistant is designed as a [vertical multi-agent system](./docs/multi-agents/introduction.md), with each agent specializing in a specific functional domain (e.g., account management, transaction history, payments). The architecture consists of the following key components:
  
- **Supervisor Agent**: Acts as a user proxy, interpreting user intent based on chat inputs and directing the request to the specific domain agent. This component ensures that user queries are efficiently handled by the relevant agent. Agents are engaged by the supervisor in a single-turn conversation, meaning that only one agent is selected by the supervisor to respond to the user task. It performs routing logic, assuming the domain agent will either complete the task in one go or request user input when additional data or action approval (like payment submission) is needed.
- **Account Agent**: Specializes in handling tasks related to banking account information, credit balance, and registered payment methods. It leverages specific Account Service APIs to fetch and manage account-related data. Semantic Kernel HTTP plugin is used to create a tool definition from the REST API YAML contract (OpenAPI specification) and automatically call the HTTP endpoint with input parameters extracted by the GPT-4 model from the chat conversation.
- **Transactions Agent**: Focuses on tasks related to querying user bank movements, including income and outgoing payments. This agent accesses the Account API to retrieve account IDs and the Transaction History service to search for transactions and present them to the user.
- **Payments Agent**: Dedicated to managing tasks related to submitting payments. It interacts with multiple APIs and tools such as Account Service (for retrieving account and payment method information), Payment Service (to submit payments), and Transaction History Service (to check for previously paid invoices).
- **Existing Business APIs**: Interfaces with the backend systems to perform operations related to personal banking accounts, transactions, and invoice payments. These APIs are implemented as external Spring Boot microservices providing the necessary data and functionality consumed by agents to execute their tasks. They are exposed both as traditional REST APIs and as MCP tools (**spring-ai-mcp**) to be consumed by agents.
- **Payments Service (Microservice)**: Offers capabilities to submit payments and notify transactions. It is a critical component for the Payments Agent to execute payment-related tasks efficiently.

- **Reporting Service (Microservice)**: Enables searching transactions and retrieving transactions by recipient. This service supports the Transactions Agent in providing detailed transaction reports to the user and the Payments Agent to check if an invoice has already been paid.
---

### Prerequisites

- Java 17 or higher 
- Maven 3.8+  
- Node.js  
- Git  
- PowerShell 7+ (for Windows users
---

